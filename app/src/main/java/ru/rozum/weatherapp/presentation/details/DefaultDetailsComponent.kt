package ru.rozum.weatherapp.presentation.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.presentation.extesions.componentScope

class DefaultDetailsComponent @AssistedInject constructor(
    private val storeFactory: DetailsStoreFactory,
    @Assisted("city") city: City,
    @Assisted("componentContext") componentContext: ComponentContext,
    @Assisted("onClickBack") private val onBackClick: () -> Unit
) : DetailsComponent,
    ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(city) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    DetailsStore.Label.ClickBack -> onBackClick()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<DetailsStore.State> = store.stateFlow

    override fun onClickBack() = store.accept(DetailsStore.Intent.ClickBack)

    override fun onClickChangeFavouriteStatus() =
        store.accept(DetailsStore.Intent.ClickChangeFavouriteStatus)

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("city") city: City,
            @Assisted("componentContext") componentContext: ComponentContext,
            @Assisted("onClickBack") onClickBack: () -> Unit
        ): DefaultDetailsComponent
    }
}