package com.mrsanglier.tsumegohero.coreui.componants.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.app.coreui.resources.common_skip
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_back
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_check
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_close
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_edit_square
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_share
import com.mrsanglier.tsumegohero.coreui.componants.actionbutton.ActionButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.actionbutton.THActionButton
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.iconbutton.FOIconButton
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.resources.THString

sealed interface TopBarAction {

    @Composable
    fun Content(modifier: Modifier = Modifier)

    data class Icon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : TopBarAction {

        @Composable
        override fun Content(modifier: Modifier) {
            FOIconButton(
                icon = icon,
                onClick = onClick,
            )
        }
    }

    data class Text(
        val label: TextSpec,
        val onClick: () -> Unit,
    ) : TopBarAction {

        @Composable
        override fun Content(modifier: Modifier) {
            THActionButton(
                text = label,
                onClick = onClick,
                style = ActionButtonStyle.Text,
            )
        }
    }

    companion object {
        fun back(onClick: () -> Unit): Icon = Icon(
            icon = THDrawable.ic_back.toIconSpec(),
            onClick = onClick,
        )

        fun close(onClick: () -> Unit): Icon = Icon(
            icon = THDrawable.ic_close.toIconSpec(),
            onClick = onClick,
        )

        fun share(onClick: () -> Unit): Icon = Icon(
            icon = THDrawable.ic_share.toIconSpec(),
            onClick = onClick,
        )

        fun skip(onClick: () -> Unit): Text = Text(
            label = THString.common_skip.toTextSpec(),
            onClick = onClick,
        )

        fun edit(onClick: () -> Unit): Icon = Icon(
            icon = THDrawable.ic_edit_square.toIconSpec(),
            onClick = onClick,
        )

        fun done(onClick: () -> Unit): Icon = Icon(
            icon = THDrawable.ic_check.toIconSpec(),
            onClick = onClick,
        )
    }
}
