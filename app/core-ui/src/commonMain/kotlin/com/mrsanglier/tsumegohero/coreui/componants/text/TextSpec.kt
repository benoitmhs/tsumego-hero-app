package com.mrsanglier.tsumegohero.coreui.componants.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Stable
sealed class TextSpec {

    abstract val annotated: AnnotatedString
        @ReadOnlyComposable @Composable
        get

    abstract val string: String
        @ReadOnlyComposable @Composable
        get

    @Composable
    @ReadOnlyComposable
    protected fun Array<out Any>.resolveArgs(): Array<out Any> {
        return Array(this.size) { idx ->
            val entry = this[idx]
            if (entry is TextSpec) {
                entry.string // marked as compile error due to Java(?)
            } else {
                this[idx]
            }
        }
    }

    class Raw(
        private val value: String,
    ) : TextSpec() {
        override val annotated: AnnotatedString
            @Composable
            @ReadOnlyComposable
            @Suppress("SpreadOperator")
            get() = AnnotatedString(string)

        override val string: String
            @Composable
            @ReadOnlyComposable
            @Suppress("SpreadOperator")
            get() = value

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (this::class != other::class) return false

            other as Raw

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        override fun toString(): String {
            return "value = $value"
        }
    }

    class Annotated(private val value: AnnotatedString) : TextSpec() {
        override val annotated: AnnotatedString
            @Composable
            @ReadOnlyComposable
            get() = value

        override val string: String
            @Composable
            @ReadOnlyComposable
            get() = value.text

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (this::class != other::class) return false

            other as Annotated

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        override fun toString(): String {
            return "value = ${value.text}"
        }
    }

    class StringRes(
        val res: StringResource,
        vararg val args: Any,
    ) : TextSpec() {
        override val annotated: AnnotatedString
            @Composable
            @Suppress("SpreadOperator")
            get() = AnnotatedString(string)

        override val string: String
            @Composable
            @Suppress("SpreadOperator")
            get() = stringResource(res, *args.resolveArgs())

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (this::class != other::class) return false

            other as StringRes

            if (res != other.res) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = res.hashCode()
            result = 31 * result + args.contentHashCode()
            return result
        }
    }

    class PluralsRes(
        val res: PluralStringResource,
        val count: Int,
        vararg val args: Any,
    ) : TextSpec() {

        override val annotated: AnnotatedString
            @Composable
            @Suppress("SpreadOperator")
            get() = AnnotatedString(string)

        override val string: String
            @Composable
            @Suppress("SpreadOperator")
            get() = pluralStringResource(res, count, *args.resolveArgs())

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (this === other) return true
            if (this::class != other::class) return false

            other as PluralsRes

            if (res != other.res) return false
            if (count != other.count) return false
            if (!args.contentEquals(other.args)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = res.hashCode()
            result = 31 * result + count
            result = 31 * result + args.contentHashCode()
            return result
        }
    }
}
