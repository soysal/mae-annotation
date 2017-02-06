package edu.brandeis.cs.nlp.mae.controller;

import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksABOUT;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksCLOSEFILE;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksLOADTASK;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksOPENFILE;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksRESETZOOM;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksSAVEXML;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksZOOMIN;
import static edu.brandeis.cs.nlp.mae.MaeHotKeys.ksZOOMOUT;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_ABOUT;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_ADDFILE;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_CLOSEFILE;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_END_ADJUD;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_LOADTASK;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_OPENFILE;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_RESET_ZOOM;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_SAVEXML;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_ZOOMIN;
import static edu.brandeis.cs.nlp.mae.MaeStrings.MENUITEM_ZOOMOUT;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import edu.brandeis.cs.nlp.mae.MaeException;
import edu.brandeis.cs.nlp.mae.controller.action.About;
import edu.brandeis.cs.nlp.mae.controller.action.ChangeFontsize;
import edu.brandeis.cs.nlp.mae.controller.action.CloseFile;
import edu.brandeis.cs.nlp.mae.controller.action.LoadTask;
import edu.brandeis.cs.nlp.mae.controller.action.MaeActionI;
import edu.brandeis.cs.nlp.mae.controller.action.OpenFile;
import edu.brandeis.cs.nlp.mae.controller.action.SaveXML;

/**
 * The Class ToolBarController.
 */
public class ToolBarController extends MaeControllerI {

	/** The tool bar. */
	private JToolBar toolBar;

	/**
	 * Instantiates a new tool bar controller.
	 *
	 * @param mainController the main controller
	 */
	public ToolBarController(MaeMainController mainController) {
		super(mainController);

		this.toolBar = new JToolBar();
		toolBar.setFloatable(false);
		createButtons();
		view.add(toolBar);

	}
	
	/**
	 * Update toolbar buttons.
	 */
	public void updateButtons() {
		toolBar.removeAll();
		createButtons();
	}
	
	/**
	 * Creates the buttons.
	 */
	private void createButtons() {
		
		// TODO: reuse actions
		// TODO: use ImageIcon for actions
        boolean taskLoaded = getMainController().isTaskLoaded();
        boolean fileLoaded = getMainController().isDocumentOpen();

        MaeActionI loadTaskAction = new LoadTask(MENUITEM_LOADTASK, null, ksLOADTASK, null, getMainController());
        String openFileLabel = getMainController().isAdjudicating() ?
                MENUITEM_ADDFILE : MENUITEM_OPENFILE;
        MaeActionI openFileAction = new OpenFile(openFileLabel, null, ksOPENFILE, null, getMainController());
        MaeActionI saveXMLAction = new SaveXML(MENUITEM_SAVEXML, null, ksSAVEXML, null, getMainController());
        String closeFileLabel = getMainController().isAdjudicating() ?
                MENUITEM_END_ADJUD : MENUITEM_CLOSEFILE;
        MaeActionI closeFileAction = new CloseFile(closeFileLabel, null, ksCLOSEFILE, null, getMainController());

        
        MaeActionI increaseFontSizeAction = new ChangeFontsize(MENUITEM_ZOOMIN, null, ksZOOMIN, null, getMainController());
        MaeActionI decreaseFontSizeAction = new ChangeFontsize(MENUITEM_ZOOMOUT, null, ksZOOMOUT, null, getMainController());
        MaeActionI resetFontSizeAction = new ChangeFontsize(MENUITEM_RESET_ZOOM, null, ksRESETZOOM, null, getMainController());

        MaeActionI aboutAction = new About(MENUITEM_ABOUT, null, ksABOUT, null, getMainController());
        
        view = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
		
		JButton btnOpenTask = new JButton("");
		btnOpenTask.setToolTipText(MENUITEM_LOADTASK);
		btnOpenTask.setIcon(new ImageIcon(getClass().getResource("/ico/task.png")));
		btnOpenTask.addActionListener(loadTaskAction);
		toolBar.add(btnOpenTask);

		toolBar.addSeparator();

		JButton btnOpen = new JButton("");
		btnOpen.setToolTipText(openFileLabel);
		btnOpen.setIcon(new ImageIcon(getClass().getResource("/ico/open.png")));
		btnOpen.addActionListener(openFileAction);
		toolBar.add(btnOpen);

		JButton btnSave = new JButton("");
		btnSave.setToolTipText(MENUITEM_SAVEXML);
		btnSave.setIcon(new ImageIcon(getClass().getResource("/ico/save.png")));
		btnSave.addActionListener(saveXMLAction);
		toolBar.add(btnSave);

		JButton btnClose = new JButton("");
		btnClose.setToolTipText(MENUITEM_SAVEXML);
		btnClose.setIcon(new ImageIcon(getClass().getResource("/ico/close.png")));
		btnClose.addActionListener(closeFileAction);
		toolBar.add(btnClose);

		btnOpen.setEnabled(taskLoaded);
		btnSave.setEnabled(fileLoaded);

        btnClose.setEnabled(fileLoaded);


		toolBar.addSeparator();
	
		JButton btnA = new JButton("");
		btnA.setToolTipText(MENUITEM_ZOOMIN);
		btnA.setIcon(new ImageIcon(getClass().getResource("/ico/font_large.png")));
		btnA.setActionCommand("+");
		btnA.addActionListener(increaseFontSizeAction);
		toolBar.add(btnA);

		JButton btnA_1 = new JButton("");
		btnA_1.setToolTipText(MENUITEM_ZOOMOUT);
		btnA_1.setIcon(new ImageIcon(getClass().getResource("/ico/font_small.png")));
		btnA_1.setActionCommand("-");
		btnA_1.addActionListener(decreaseFontSizeAction);
		toolBar.add(btnA_1);	

		JButton btnA_2 = new JButton("");
		btnA_2.setToolTipText(MENUITEM_RESET_ZOOM);
		btnA_2.setIcon(new ImageIcon(getClass().getResource("/ico/font_small.png")));
		btnA_2.setActionCommand("0");
		btnA_2.addActionListener(resetFontSizeAction);
		toolBar.add(btnA_2);
		
		toolBar.addSeparator();
		
		JButton btnAbout = new JButton("");
		btnAbout.setToolTipText(MENUITEM_ABOUT);
		btnAbout.setIcon(new ImageIcon(getClass().getResource("/ico/info.png")));
		btnAbout.addActionListener(aboutAction);
		toolBar.add(btnAbout);
	}

	/* (non-Javadoc)
	 * @see edu.brandeis.cs.nlp.mae.controller.MaeControllerI#addListeners()
	 */
	@Override
	void addListeners() throws MaeException {

	}
}
