//package com.joatan.pdfmapper.ui;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import java.awt.BorderLayout;
//import javax.swing.JTree;
//import javax.swing.event.TreeSelectionEvent;
//import javax.swing.event.TreeSelectionListener;
//import javax.swing.tree.DefaultTreeModel;
//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.JButton;
//import java.io.File;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class Main {
//
//	private JFrame frame; // main frame
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Main window = new Main();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public Main() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	@SuppressWarnings("serial")
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 792, 620);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		JPanel panel = new JPanel();
//		frame.getContentPane().add(panel, BorderLayout.CENTER);
//		panel.setLayout(null);
//
//		// Creating the file tree
//		JTree tree = new JTree();
//		tree.addTreeSelectionListener(new TreeSelectionListener() {
//			public void valueChanged(TreeSelectionEvent e) {
//				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
//				System.out.println("You selected " + node);
//			}
//		});
//
//		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Home") {
//			{
//				File fileRoot = new File(System.getProperty("user.dir"));
//				DefaultMutableTreeNode root;
//				root = new DefaultMutableTreeNode(fileRoot);
//
//				createChildren(fileRoot, root);
//
////						node_1.add(new DefaultMutableTreeNode("blue"));
////						node_1.add(new DefaultMutableTreeNode("violet"));
////						node_1.add(new DefaultMutableTreeNode("red"));
////						node_1.add(new DefaultMutableTreeNode("yellow"));
//				add(root);
//
//			}
//		}));
//
//		tree.setBounds(80, 62, 298, 405);
//		panel.add(tree);
//
//		JButton btnNewButton = new JButton("New button");
//		btnNewButton.addMouseListener(new MouseAdapter() {
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				Path currentRelativePath = Paths.get("");
//				String s = currentRelativePath.toAbsolutePath().toString();
//				String treeList[] = new File(s).list();
//				for (String x : treeList) {
//					System.out.println(x);
//				}
//			}
//		});
//		btnNewButton.setBounds(468, 135, 89, 23);
//		panel.add(btnNewButton);
//	}
//
//	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
//		File[] files = fileRoot.listFiles();
//		if (files == null)
//			return;
//
//		for (File file : files) {
//			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
//			
//			String fileName = file.getName();
//            String extension = "";
//            int i = fileName.lastIndexOf('.');
//  
//            if (i > 0) {
//                extension = fileName.substring(i + 1);
//                if(extension.equals("pdf"))
//                	node.add(childNode);
//            }
//            
//			if (file.isDirectory()) {
//				createChildren(file, childNode);
//			}
//		}
//	}
//}
//
//class FileNode {
//
//	private File file;
//
//	public FileNode(File file) {
//		this.file = file;
//	}
//
//	//Making it mode readable filename for the tree
//	@Override
//	public String toString() {
//		String name = file.getName();
//		if (name.equals("")) {
//			return file.getAbsolutePath();
//		} else {
//			return name;
//		}
//	}
//}
//
