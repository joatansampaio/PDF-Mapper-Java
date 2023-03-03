package com.joatan.pdfmapper.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import com.joatan.pdfmapper.components.Button;
import com.joatan.pdfmapper.components.FolderChooser;
import com.joatan.pdfmapper.handlers.ExcelHandler;

/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private SourceTree sourceTree;
	private DestinationTree destinationTree;

	public MainPanel() {

		sourceTree = new SourceTree();
		destinationTree = new DestinationTree();
		
		JScrollPane sourceTreePanel = new JScrollPane();
		JScrollPane destinationTreePanel = new JScrollPane();
		
		sourceTreePanel.setPreferredSize(new Dimension(300,700));
		destinationTreePanel.setPreferredSize(new Dimension(300,700));

		JLabel label = new JLabel("Select PDFs to Add/Remove");
		JButton browseBtn = new Button("Browse Files");
		JButton executeBtn = new Button("Create Spreadsheet");
		JButton addBtn = new Button("Add to List | >>");
		JButton removeAllBtn = new Button(" << | Remove All");
		JButton removeBtn = new Button(" << | Remove from list");
		JPanel btnPanel = new JPanel();
		JPanel centerPanel = new JPanel(new GridBagLayout());

		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Button panel config
		btnPanel.setLayout(new GridLayout(0, 1, 10, 10));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		btnPanel.add(browseBtn);
		btnPanel.add(Box.createHorizontalStrut(10));
		btnPanel.add(label);
		btnPanel.add(addBtn);
		btnPanel.add(removeBtn);
		btnPanel.add(removeAllBtn);
		btnPanel.add(executeBtn);

		GridBagConstraints centerPanelConfig = new GridBagConstraints();
		centerPanelConfig.gridx = 0;
		centerPanelConfig.gridy = 0;
		centerPanelConfig.fill = GridBagConstraints.CENTER;
		
		centerPanel.add(btnPanel, centerPanelConfig);
		
		// Listeners
		browseBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FolderChooser folderChooser = new FolderChooser();
				sourceTree.populateTree(new File(folderChooser.selectFolder()));
			}
		});

		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sourceTree.addSelectedNodesToDestinationTree();
			}
		});

		removeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				destinationTree.removeSelectedNodes();
			}
		});
		
		removeAllBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				destinationTree.removeAllNodes();
			}
		});

		executeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ArrayList<String> srcPaths = new ArrayList<String>();
				ExcelHandler excel = new ExcelHandler();
				excel.createWorkbook();
				
				int numberOfFiles = destinationTree.getRoot().getChildCount();
				if (numberOfFiles != 0) {
					for (int index = 0; index < numberOfFiles; index++) {
						DefaultMutableTreeNode child = (DefaultMutableTreeNode) destinationTree.getRoot()
								.getChildAt(index);
						String filePath = child.getUserObject().toString();
						File file = new File(filePath);
						String absolutePath = file.getAbsolutePath();
						System.out.println(absolutePath);
						srcPaths.add(absolutePath);
					}
				}

				int rowNumber = 1;
				for (String s : srcPaths) {
					try {
						excel.addStudent(s, rowNumber);
						rowNumber++;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		// Adding tree, browse button and the panel
		sourceTreePanel.getViewport().add(sourceTree);
		destinationTreePanel.getViewport().add(destinationTree);
		
		add(sourceTreePanel);
		add(centerPanel);
		add(destinationTreePanel);
	}

	public SourceTree getSourceTree() {
		return sourceTree;
	}

	public DestinationTree getDestinationTree() {
		return destinationTree;
	}

}
