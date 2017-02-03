package org.bh.game.snek.gui.swing

import org.bh.game.snek.game.logic.SnekGameStateController
import org.bh.game.snek.state.BaseSnekDataView
import org.bh.game.snek.state.SnekData
import org.bh.tools.base.struct.UIViewController
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

/**
 * Copyright BHStudios ©2016 BH-1-PS. Made for Snek.
 *
 * @author Kyli Rouge
 * @since 2016-11-05
 */
class SnekViewController(override val view: SnekView, val controller: SnekGameStateController, val keymap: Keymap):
//        KeyListener,
        UIViewController<SnekView> {
    constructor(snekDataView: BaseSnekDataView, mutator: SnekGameStateController, keymap: Keymap): this(SnekView(snekDataView), mutator, keymap)
    constructor(snekData: SnekData, mutator: SnekGameStateController, keymap: Keymap): this(BaseSnekDataView(snekData), mutator, keymap)

    init {
//        view.addKeyListener(this)
        view.isFocusCycleRoot = true
        view.grabFocus()

        keymap.registerAll(this) {
            //{print(it)}
            object: AbstractAction() {
                override fun actionPerformed(e: ActionEvent?) {
                    performAction(it)
                }
            }
        }
    }

//    override fun keyTyped(e: KeyEvent?) {
//        if (e != null) performActionForKeyEvent(e, onKeyTyped)
//    }
//
//    override fun keyPressed(e: KeyEvent?) {
//        if (e != null) performActionForKeyEvent(e, onKeyDown)
//    }
//
//    override fun keyReleased(e: KeyEvent?) {
//        if (e != null) performActionForKeyEvent(e, onKeyUp)
//    }
//
//    private fun performActionForKeyEvent(e: KeyEvent, trigger: KeyActionTrigger) {
////        val action = keymap.map.entries.safeFirst { 0 != (it.value and e.extendedKeyCode) }?.key
//        val actions = keymap.actionsForKeyEvent(e, trigger)
//        val appropriateAction = controller.appropriateAction(actions)
//        if (appropriateAction != null) performAction(appropriateAction)
//    }

    private fun performAction(action: SnekAction) {
        controller.mutate(action)
        view.representedObject = controller.currentState().dataView
    }
}