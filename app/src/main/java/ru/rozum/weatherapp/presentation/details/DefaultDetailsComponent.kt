package ru.rozum.weatherapp.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.presentation.extesions.componentScope
import javax.inject.Inject

class DefaultDetailsComponent @Inject constructor(
    private val storeFactory: DetailsStoreFactory,
    city: City,
    componentContext: ComponentContext,
    private val onClickBack: () -> Unit
) : DetailsComponent,
    ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(city) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    DetailsStore.Label.ClickBack -> onClickBack()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<DetailsStore.State> = store.stateFlow

    override fun onClickBack() = store.accept(DetailsStore.Intent.ClickBack)

    override fun onClickChangeFavouriteStatus() =
        store.accept(DetailsStore.Intent.ClickChangeFavouriteStatus)
}