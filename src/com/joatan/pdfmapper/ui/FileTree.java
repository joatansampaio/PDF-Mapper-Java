package com.joatan.pdfmapper.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class FileTree extends JTree {
	  /** Construct a FileTree */
	  public FileTree(File dir) {
	    setLayout(new BorderLayout());

	    // Make a tree list with all the nodes, and make it a JTree
	    JTree tree = new JTree(addNodes(null, dir));

	    // Add a listener
	    tree.addTreeSelectionListener(new TreeSelectionListener() {
	      public void valueChanged(TreeSelectionEvent e) {
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
	            .getPath().getLastPathComponent();
	        System.out.println("You selected " + node);
	      }
	    });
	    
	    
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) this.getCellRenderer();
		Icon closedIcon = new ImageIcon("C:\\Users\\Joatan\\Documents\\GitHub\\pdf-mapper-java\\PDF Mapper\\src\\com\\joatan\\pdfmapper\\images\\folderclosed.png");
		Icon openIcon = new ImageIcon("C:\\Users\\Joatan\\Documents\\GitHub\\pdf-mapper-java\\PDF Mapper\\src\\com\\joatan\\pdfmapper\\images\\folderclosed.png");
		Icon leafIcon = new ImageIcon("C:\\Users\\Joatan\\Documents\\GitHub\\pdf-mapper-java\\PDF Mapper\\src\\com\\joatan\\pdfmapper\\images\\folderclosed.png");
		renderer.setClosedIcon(closedIcon);
		renderer.setOpenIcon(openIcon);
		renderer.setLeafIcon(leafIcon);
	    

	    // Lastly, put the JTree into a JScrollPane.
	    JScrollPane scrollpane = new JScrollPane();
	    scrollpane.getViewport().add(tree);
	    add(BorderLayout.CENTER, scrollpane);
	  }

	  /** Add nodes from under "dir" into curTop. Highly recursive. */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
	    String curPath = dir.getPath();
	    System.out.println(curPath);
	    DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);
	    if (curTop != null) { // should only be null at root
	      curTop.add(curDir);
	    }
	    Vector ol = new Vector();
	    String[] tmp = dir.list();
	    for (int i = 0; i < tmp.length; i++)
	      ol.addElement(tmp[i]);
	    Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
	    File f;
	    Vector files = new Vector();
	    // Make two passes, one for Dirs and one for Files. This is #1.
	    for (int i = 0; i < ol.size(); i++) {
	      String thisObject = (String) ol.elementAt(i);
	      String newPath;
	      if (curPath.equals("."))
	        newPath = thisObject;
	      else
	        newPath = curPath + File.separator + thisObject;
	      if ((f = new File(newPath)).isDirectory())
	        addNodes(curDir, f);
	      else
	        files.addElement(thisObject);
	    }
	    // Pass two: for files.
	    for (int fnum = 0; fnum < files.size(); fnum++)
	      curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
	    return curDir;
	  }
	  
	  

	  /** Main: make a Frame, add a FileTree */

	}