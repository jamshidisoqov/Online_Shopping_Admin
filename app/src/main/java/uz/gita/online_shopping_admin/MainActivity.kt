package uz.gita.online_shopping_admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.online_shopping_admin.navigation.NavigationHandler
import uz.gita.online_shopping_admin.showed.Showed
import uz.gita.online_shopping_admin.ui.dialogs.ProgressDialog
import uz.gita.online_shopping_admin.utils.extensions.showErrorDialog
import uz.gita.online_shopping_admin.utils.extensions.showMessageDialog
import uz.gita.online_shopping_admin.utils.extensions.toast
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var showed: Showed

    private lateinit var dialog: ProgressDialog

    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navigationHandler.navigationStack
            .onEach { it.invoke(fragment.findNavController()) }
            .launchIn(lifecycleScope)

        dialog = ProgressDialog(this)
        showed.showedLoadingFlow.debounce(1500L).onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)
        showed.showedMessageFlow.debounce(1500L).onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)
        showed.showedErrorFlow.debounce(1500L).onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)
        showed.toastFlow.debounce(1500L).onEach {
            toast(it)
        }.launchIn(lifecycleScope)

    }

    private fun showProgress() {
        dialog.show()
    }


    private fun hideProgress() {
        dialog.cancel()
    }
}