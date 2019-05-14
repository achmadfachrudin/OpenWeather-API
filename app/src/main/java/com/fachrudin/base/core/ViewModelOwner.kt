package com.fachrudin.base.core

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
interface ViewModelOwner<T : BaseViewModel> {
    val viewModel: T
}
