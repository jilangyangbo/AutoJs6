package org.autojs.autojs.theme.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import org.autojs.autojs.theme.ThemeColor
import org.autojs.autojs.theme.ThemeColorManager.add
import org.autojs.autojs.theme.ThemeColorMutable
import org.autojs.autojs.util.ViewUtils
import org.autojs.autojs.util.ViewUtils.applyColorFilterWith
import org.autojs.autojs.util.ViewUtils.onceGlobalLayout
import org.autojs.autojs6.R

/**
 * Created by Stardust on Mar 5, 2017.
 * Modified by SuperMonster003 as of Dec 1, 2021.
 */
open class ThemeColorToolbar : Toolbar, ThemeColorMutable {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        add(this)
        setContentInsetStartWithNavigation(context.resources.getDimensionPixelSize(R.dimen.toolbar_content_inset_start_with_navigation))
        setTitleTextAppearance(context, R.style.TextAppearanceMainTitle)
    }

    override fun setThemeColor(themeColor: ThemeColor) {
        onceGlobalLayout { applyThemeColor(themeColor) }
    }

    private fun applyThemeColor(themeColor: ThemeColor) {
        val currentThemeColor = themeColor.colorPrimary
        val tintColor = ViewUtils.getDayOrNightColorByLuminance(context, currentThemeColor)

        setBackgroundColor(currentThemeColor)
        setTitleTextColor(tintColor)
        setSubtitleTextColor(tintColor)

        navigationIcon?.let { navigationIcon = it.applyColorFilterWith(tintColor) }
        collapseIcon?.let { collapseIcon = it.applyColorFilterWith(tintColor) }
        overflowIcon?.let { overflowIcon = it.applyColorFilterWith(tintColor) }
    }
}
