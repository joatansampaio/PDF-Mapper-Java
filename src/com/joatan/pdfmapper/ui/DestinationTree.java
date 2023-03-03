package com.joatan.pdfmapper.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
@SuppressWarnings("serial")
public class DestinationTree extends JTree {

	public DestinationTree() {
		this.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Selected PDFs")));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTree tree = (JTree) e.getSource();
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					if (selectedNode != null) {
						// Remove the selected node from the destination tree
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
						if (parent != null) {
							parent.remove(selectedNode);
							((DefaultTreeModel) tree.getModel()).reload(parent);
						}
					}
				}
			}
		});
	}

	public void removeAllNodes() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();
		while (root.getChildCount() > 0) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(0);
			child.removeFromParent();
		}
		((DefaultTreeModel) this.getModel()).reload();
	}

	public void removeSelectedNodes() {
	    TreePath[] selectedPaths = getSelectionPaths();
	    if (selectedPaths != null) {
	        for (TreePath selectedPath : selectedPaths) {
	            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
	            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
	            if (parentNode != null) {
	                parentNode.remove(selectedNode);
	            }
	        }
	        ((DefaultTreeModel) getModel()).reload();
	    }
	}
	
	public DefaultMutableTreeNode getRoot() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();
		return root;
	}


}
