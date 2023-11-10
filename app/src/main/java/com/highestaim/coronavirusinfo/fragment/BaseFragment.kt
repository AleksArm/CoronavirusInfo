package com.highestaim.coronavirusinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import org.koin.core.parameter.ParametersHolder
import org.koin.core.parameter.emptyParametersHolder
import kotlin.reflect.KClass

abstract class BaseFragment<VBinding : ViewBinding, VModel : ViewModel>(
    viewModelClass: KClass<VModel>,
    savedStateOwner: FragmentSavedStateOwner = FragmentSavedStateOwner.PARENT_ACTIVITY
) : Fragment() {

    protected lateinit var binding: VBinding

    protected open val viewModel: VModel by lazy {
        val storeOwner: ViewModelStoreOwner = when (savedStateOwner) {
            FragmentSavedStateOwner.SELF -> this
            FragmentSavedStateOwner.PARENT_ACTIVITY -> activity as ViewModelStoreOwner
            FragmentSavedStateOwner.PARENT_FRAGMENT -> parentFragment as ViewModelStoreOwner
        }
        viewModelForClass(
            owner = {
                storeOwner
            },
            parameters = { viewModelParams },
            clazz = viewModelClass
        ).value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = binding.root

    // override in case custom params needed
    protected open val viewModelParams: ParametersHolder = emptyParametersHolder()

    protected abstract fun getViewBinding(): VBinding

    protected abstract fun initUI(savedInstanceState: Bundle?)

    protected abstract fun subscribeToViewModel(viewModel: VModel)

    abstract fun getTitle(): String

    abstract fun setupToolbar()

}