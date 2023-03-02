package com.joatan.pdfmapper.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import com.joatan.pdfmapper.handlers.ExcelHandler;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	private static PDFFileTree sourceTree;
	private static DestinationPDFFileTree destinationTree;

	public MainPanel() {

		// Variables
		sourceTree = new PDFFileTree();
		destinationTree = new DestinationPDFFileTree();

		JLabel label = new JLabel("Double Click to add or remove files");
		JButton browseBtn = new JButton("Browse Files");
		JButton executeBtn = new JButton("Execute Script");
		JButton addBtn = new JButton("Add to List >>");
		JButton removeAllBtn = new JButton(" << Remove All");
		JPanel btnPanel = new JPanel();

		// Button panel config
		btnPanel.setLayout(new GridLayout(0, 1));
		btnPanel.add(label);
		btnPanel.add(Box.createHorizontalStrut(10));
		btnPanel.add(addBtn);
		btnPanel.add(removeAllBtn);
		btnPanel.add(browseBtn);
		btnPanel.add(executeBtn);
		btnPanel.add(Box.createHorizontalStrut(10));

		Dimension buttonSize = new Dimension(200, 50);

		addBtn.setPreferredSize(buttonSize);
		removeAllBtn.setPreferredSize(buttonSize);
		executeBtn.setPreferredSize(buttonSize);
		browseBtn.setPreferredSize(buttonSize);

		// Listeners
		browseBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FolderChooser folderChooser = new FolderChooser();
				sourceTree.populateTree(new File(folderChooser.selectFolder()));
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
						TreeNode[] path = child.getPath();

						for (TreeNode t : path) {
							if (t != null && !t.toString().equals(""))
								srcPaths.add(t.toString());
						}
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
//				try {
//					excel.addStudent("student1.pdf", 1);
//					excel.addStudent("student2.pdf", 2);
//					excel.addStudent("student3.pdf", 3);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

			}
		});
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sourceTree.addSelectedNodesToDestinationTree();
			}
		});

		removeAllBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				destinationTree.removeAllNodes();
			}
		});

		// Adding tree, browse button and the panel
		add(sourceTree);
		add(btnPanel);
		add(destinationTree);
	}

	public static PDFFileTree getSourceTree() {
		return sourceTree;
	}

	public static DestinationPDFFileTree getDestinationTree() {
		return destinationTree;
	}

}
