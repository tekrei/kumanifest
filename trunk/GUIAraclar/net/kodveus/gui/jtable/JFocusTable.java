package net.kodveus.gui.jtable;

import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;


/** JTable that uses transferFocus instead of requestFocus to handle
 *      focus on editorcomponents.
 *      Necessary changes in init against JTable:
 *        1) isFocusCycleRoot
 *        2) uses LayoutFocusTraversalPolicy with implicitDownCycle turned off
 *                      (better even: use a traversalPolicy that does a post-implicit-downCycle)
 *
 *      fixed: #4684090 - focus incorrectly transfered to outside of table
 *              after terminating edit in editable combo
 *      fixed: #?? - editable combo not available for keyboard input after starting
 *              edit with f2
 *      fixed: #4109871 - JComboBox is not fully accessible in JTable.
 *
 *
 *      @author (C) 2002 Jeanette Winzenburg
 *
 *      $RCSfile: JFocusTable.java,v $ $Revision: 1.1 $ $Date: 2006-07-20 16:57:44 $
 *
 *
 */
public class JFocusTable extends JTable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean reallySurrendersFocus;

    public JFocusTable() {
        super();
    }

    public JFocusTable(int rows, int columns) {
        super(rows, columns);
    }

    public JFocusTable(TableModel model) {
        super(model);
    }

    /** to circumvent super's handling
     */
    public void setSurrendersFocusOnKeystroke(boolean surrender) {
        reallySurrendersFocus = surrender;
    }

    public boolean isReallySurrendersFocusEnabled() {
        return reallySurrendersFocus;
    }

    /** bending the contract a bit: always return false for use in super
     */
    public boolean getSurrendersFocusOnKeystroke() {
        return false;
    }

    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        // copied from super: removed call to editorComp.setNextFocusableComponent
        // having the issues:
        // 1. triggers the usage of LegacyFocusTraversalPolicy
        // 2. was incomplete anyway because did not check for children of editorComp
        Object value = getValueAt(row, column);
        boolean isSelected = isCellSelected(row, column);

        return editor.getTableCellEditorComponent(this, value, isSelected, row,
            column);
    }

    /** starts editing at the anchor cell. Transfers focus to the editor if
     *      transferfocus.
     *      pre: !isEditing()
     */
    public void startEditing(boolean transferFocus) {
        ListSelectionModel rsm = getSelectionModel();
        int anchorRow = rsm.getAnchorSelectionIndex();
        ListSelectionModel csm = getColumnModel().getSelectionModel();
        int anchorColumn = csm.getAnchorSelectionIndex();
        boolean startedEdit = editCellAt(anchorRow, anchorColumn);

        if (startedEdit && transferFocus) {
            transferFocusToEditor();
        }
    }

    /** pre: isEditing()
     */
    public void stopEditing(boolean grabFocus) {
        if (!getCellEditor().stopCellEditing()) {
            return;
        }

        if (grabFocus) {
            requestFocus();
        }
    }

    protected boolean processKeyBinding(KeyStroke ks, KeyEvent e,
        int condition, boolean pressed) {
        boolean notWasEditing = !isEditing();
        boolean result = super.processKeyBinding(ks, e, condition, pressed);

        if (notWasEditing && isEditing() && isReallySurrendersFocusEnabled()) {
            transferFocusToEditor();
        }

        return result;
    }

    //---------------------------helper

    /** does its best to transferFocus to the editorComp.
     *      pre: isEditing()
     */
    protected void transferFocusToEditor() {
        if (getManager().getCurrentFocusCycleRoot() != this) {
            transferFocusDownCycle();
        }

        transferFocus();
    }

    private KeyboardFocusManager getManager() {
        return KeyboardFocusManager.getCurrentKeyboardFocusManager();
    }

    //---------------------------init
    protected void initializeLocalVars() {
        super.initializeLocalVars();
        initEditProperties();
    }

    protected void initEditProperties() {
        putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        setSurrendersFocusOnKeystroke(true);
        setFocusCycleRoot(true);
        getActionMap().put("startEditing", createToggleEditAction());
        setFocusTraversalPolicy(createTablePolicy());
    }

    //---------------------------factory methods
    protected FocusTraversalPolicy createTablePolicy() {
        LayoutFocusTraversalPolicy policy = new LayoutFocusTraversalPolicy();
        policy.setImplicitDownCycleTraversal(false);

        return policy;
    }

    protected Action createToggleEditAction() {
        return new ToggleEditAction();
    }

    //---------------------------ToggleEditAction
    private class ToggleEditAction extends AbstractAction {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public void actionPerformed(ActionEvent e) {
            if (isEditing()) {
                stopEditing(true);
            } else {
                startEditing(true);
            }
        }
    }
}
