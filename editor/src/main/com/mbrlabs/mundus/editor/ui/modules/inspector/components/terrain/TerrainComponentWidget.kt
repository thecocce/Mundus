/*
 * Copyright (c) 2016. See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mbrlabs.mundus.editor.ui.modules.inspector.components.terrain

import com.kotcrab.vis.ui.widget.VisTable
import com.kotcrab.vis.ui.widget.tabbedpane.Tab
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneListener
import com.mbrlabs.mundus.commons.scene3d.GameObject
import com.mbrlabs.mundus.commons.scene3d.components.Component
import com.mbrlabs.mundus.editor.scene3d.components.TerrainComponent
import com.mbrlabs.mundus.editor.ui.modules.inspector.components.ComponentWidget

/**
 * @author Marcus Brummer
 * @version 29-01-2016
 */
class TerrainComponentWidget(terrainComponent: TerrainComponent) : ComponentWidget<TerrainComponent>("Terrain Component", terrainComponent),
        TabbedPaneListener {

    private var tabbedPane: TabbedPane? = null
    private val tabContainer = VisTable()

    private var raiseLowerTab: TerrainUpDownTab? = null
    private var flattenTab: TerrainFlattenTab? = null
    private var paintTab: TerrainPaintTab? = null
    private var genTab: TerrainGenTab? = null
    private var settingsTab: TerrainSettingsTab? = null

    init {
        tabbedPane = TabbedPane()
        tabbedPane!!.addListener(this)

        raiseLowerTab = TerrainUpDownTab(this)
        flattenTab = TerrainFlattenTab(this)
        paintTab = TerrainPaintTab(this)
        genTab = TerrainGenTab(this)
        settingsTab = TerrainSettingsTab()

        tabbedPane!!.add(raiseLowerTab!!)
        tabbedPane!!.add(flattenTab!!)
        tabbedPane!!.add(paintTab!!)
        tabbedPane!!.add(genTab!!)
        tabbedPane!!.add(settingsTab!!)

        collapsibleContent.add(tabbedPane!!.table).growX().row()
        collapsibleContent.add(tabContainer).expand().fill().row()
        tabbedPane!!.switchTab(0)
    }

    override fun setValues(go: GameObject) {
        val c = go.findComponentByType(Component.Type.TERRAIN)
        if (c != null) {
            this.component = c as TerrainComponent
        }
    }

    override fun switchedTab(tab: Tab) {
        tabContainer.clearChildren()
        tabContainer.add(tab.contentTable).expand().fill()
    }

    override fun removedTab(tab: Tab) {
        // no
    }

    override fun removedAllTabs() {
        // nope
    }

}
