package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_import
import com.mrsanglier.tsumegohero.coreui.componants.bottombar.THBottomBar
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.screen.LocalPiScreenPadding
import com.mrsanglier.tsumegohero.coreui.componants.screen.THScreen
import com.mrsanglier.tsumegohero.coreui.extension.rememberBottomBarElevation
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.dashboard.screens.play.composable.TsumegoItemCell
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import io.github.vinceglb.filekit.dialogs.FileKitMode
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.compose.rememberFilePickerLauncher
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlayRoute(
    navScope: PlayNavScope,
    viewModel: PlayViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val fileKitLauncher = rememberFilePickerLauncher(
        mode = FileKitMode.Multiple(),
        type = FileKitType.File(extensions = listOf("txt", "sgf")),
    ) { files ->
        files?.let(viewModel::saveTsumegoFiles)
    }

    PlayScreen(
        uiState = uiState,
        navigateToGame = navScope.navigateToGame,
        onClickImport = fileKitLauncher::launch,
    )
}

@Composable
private fun PlayScreen(
    uiState: PlayViewModelState,
    navigateToGame: (String) -> Unit,
    onClickImport: () -> Unit,
) {
    val lazyState = rememberLazyListState()
    val bottomBarHazeState = remember { HazeState() }
    val bottomBarElevation by rememberBottomBarElevation(lazyState)

    THScreen(
        modifier = Modifier.padding(
            bottom = LocalPiScreenPadding.current.calculateBottomPadding(),
        ),
        bottomBar = {
            THBottomBar(
                hazeState = bottomBarHazeState,
                elevation = bottomBarElevation,
                secondaryButton = THButtonState(
                    text = "Import sgf".toTextSpec(), // TODO: loco
                    onClick = onClickImport,
                    icon = THDrawable.ic_import.toIconSpec(),
                ),
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .hazeSource(bottomBarHazeState)
                .padding(innerPadding),
            state = lazyState,
        ) {
            TsumegoItemCell.lazyItems(
                scope = this,
                items = uiState.tsugemoItems,
                onClickItem = navigateToGame,
            )
        }
    }
}
