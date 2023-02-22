package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class UserInterface extends JFrame {

/////	GLOBAL VARIABLES //////
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static String size;
	public static String type;
	public static int qty;
	public static int index;
	public boolean transactStatus = true;
	private JTextField textField;
	static JLabel orderTotal = new JLabel(Resources.emptyNo);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void getOrder() {
		if (type == Resources.coffee) {
			Functions.coffee();
			double number = Functions.orderTotalCost;
			String stringTotal = number + Resources.convertToString;
			orderTotal.setText(stringTotal);
		} else if (type == Resources.juice) {
			Functions.juice();
			double number = Functions.orderTotalCost;
			String stringTotal = number + Resources.convertToString;
			orderTotal.setText(stringTotal);
		} else if (type == Resources.cheesecake) {
			Functions.cheesecake();
			double number = Functions.orderTotalCost;
			String stringTotal = number + Resources.convertToString;
			orderTotal.setText(stringTotal);
		} else if (type == Resources.spongecake) {
			Functions.spongecake();
			double number = Functions.orderTotalCost;
			String stringTotal = number + Resources.convertToString;
			orderTotal.setText(stringTotal);
		}
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public UserInterface() {
		setTitle(Resources.order);
		setResizable(false);
		setBounds(100, 100, 639, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		bottom options panel
		final JPanel qtySlider = new JPanel();

		JPanel orderBottomOptions = new JPanel();
		orderBottomOptions.setBounds(5, 491, 628, 91);
		contentPane.add(orderBottomOptions);
		orderBottomOptions.setBorder(new LineBorder(Color.BLACK));
		orderBottomOptions.setLayout(null);

		final JLabel errorText = new JLabel(Resources.convertToString);
		errorText.setBounds(256, 37, 212, 16);
		orderBottomOptions.add(errorText);
		errorText.setVisible(false);

//		reciept labels
		final JLabel changeLabel = new JLabel(Resources.change);
		final JLabel changeValue = new JLabel(Resources.convertToString);
		changeLabel.setVisible(false);
		changeValue.setVisible(false);

//		pay button
		JButton payOrder = new JButton(Resources.pay);
		payOrder.setBounds(133, 56, 116, 29);
		orderBottomOptions.add(payOrder);
		payOrder.setBackground(Color.DARK_GRAY);
		payOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (transactStatus == true) {
					try {
						String text = textField.getText();
						double amount = Double.parseDouble(text);
						if (amount >= Functions.orderTotalCost) {
							textField.setText(Resources.convertToString);
							errorText.setVisible(false);
							changeLabel.setVisible(true);
							changeValue.setVisible(true);
							String change = amount - Functions.orderTotalCost + Resources.convertToString;
							changeValue.setText(change);
							transactStatus = false;
						} else if (Functions.orderTotalCost == 0.0) {
							errorText.setVisible(true);
							textField.setText(Resources.errorMessageFour);
						} else {
							errorText.setVisible(true);
							textField.setText(Resources.convertToString);
							errorText.setText(Resources.errorMessageThree);
						}
					} catch (Exception err) {
						errorText.setVisible(true);
						textField.setText(Resources.convertToString);
						errorText.setText(Resources.errorMessageTwo);
						System.out.println(err);
					}
				} else {
					textField.setText(Resources.convertToString);
					errorText.setVisible(true);
					errorText.setText(Resources.errorMessageOne);
				}
			}
		});

//		input amount
		textField = new JTextField();
		textField.setBounds(137, 28, 107, 29);
		orderBottomOptions.add(textField);
		textField.setColumns(10);

		JButton newOrder = new JButton(Resources.newOrder);
		newOrder.setBounds(6, 7, 123, 78);
		orderBottomOptions.add(newOrder);
		newOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Functions.orderLists.clear();
				Functions.orderTotalCost = 0;
				orderTotal.setText(Resources.emptyNo);
				changeLabel.setVisible(false);
				changeValue.setVisible(false);
				transactStatus = true;
			}
		});

		JLabel enterAmount = new JLabel(Resources.enterAmount);
		enterAmount.setBounds(140, 10, 89, 16);
		orderBottomOptions.add(enterAmount);

		JPanel orderSectionInfo = new JPanel();
		orderSectionInfo.setBounds(411, 18, 223, 469);
		contentPane.add(orderSectionInfo);
		orderSectionInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		orderSectionInfo.setLayout(null);

//		reciept info section
		JLabel orderLabel = new JLabel(Resources.order);
		orderLabel.setBounds(88, 6, 43, 21);
		orderSectionInfo.add(orderLabel);

		JLabel orderTotalLabel = new JLabel(Resources.total);
		orderTotalLabel.setBounds(6, 427, 43, 16);
		orderSectionInfo.add(orderTotalLabel);

		orderTotal.setBounds(51, 427, 80, 16);
		orderSectionInfo.add(orderTotal);

		changeLabel.setBounds(6, 447, 51, 16);
		orderSectionInfo.add(changeLabel);

		changeValue.setBounds(61, 447, 61, 16);
		orderSectionInfo.add(changeValue);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 399, 218, 67);
		orderSectionInfo.add(separator);

//		order list
		@SuppressWarnings("rawtypes")
		final JList list = new JList(Functions.orderLists);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = list.getSelectedIndex();
				Functions.remove();
				double number = Functions.orderTotalCost;
				String stringTotal = number + Resources.convertToString;
				orderTotal.setText(stringTotal);
			}
		});

		list.setToolTipText(Resources.convertToString);
		list.setBounds(6, 39, 210, 362);
		orderSectionInfo.add(list);

//		size modal
		final JPanel sizeModal = new JPanel();
		sizeModal.setBounds(286, 18, 122, 338);
		contentPane.add(sizeModal);
		sizeModal.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		sizeModal.setLayout(null);
		sizeModal.setVisible(false);
		sizeModal.setBackground(new Color(0, 0, 0, 64));

//		size modal label
		JLabel sizeModalLabel = new JLabel(Resources.selectSize);
		sizeModalLabel.setBounds(24, 19, 66, 16);
		sizeModal.add(sizeModalLabel);

//		size buttons
		JButton smallSize = new JButton(Resources.small);
		smallSize.setBounds(6, 47, 105, 29);
		sizeModal.add(smallSize);
		smallSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeModal.setVisible(false);
				qtySlider.setVisible(false);
				size = Resources.small;
				getOrder();
			}
		});

		JButton mediumSize = new JButton(Resources.medium);
		mediumSize.setBounds(6, 76, 105, 29);
		sizeModal.add(mediumSize);
		mediumSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeModal.setVisible(false);
				qtySlider.setVisible(false);
				size = Resources.medium;
				getOrder();
			}
		});

		JButton largeSize = new JButton(Resources.large);
		largeSize.setBounds(6, 103, 105, 29);
		sizeModal.add(largeSize);
		largeSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeModal.setVisible(false);
				qtySlider.setVisible(false);
				size = Resources.large;
				getOrder();
			}
		});

//		close size modal btn
		JButton closeSizeModal = new JButton(Resources.cancel);
		closeSizeModal.setBounds(6, 166, 105, 29);
		sizeModal.add(closeSizeModal);
		closeSizeModal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeModal.setVisible(false);
			}
		});

//		qty size border & label
		qtySlider.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		qtySlider.setBounds(5, 360, 403, 127);
		contentPane.add(qtySlider);
		qtySlider.setLayout(null);
		qtySlider.setVisible(false);

		JLabel enterQtyLabel = new JLabel(Resources.enterQty);
		enterQtyLabel.setBounds(145, 6, 90, 16);
		qtySlider.add(enterQtyLabel);

//		quantity slider
		final JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setMinimum(1);
		slider.setMinorTickSpacing(5);
		slider.setMaximum(20);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setBounds(6, 34, 386, 44);
		qtySlider.add(slider);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				qty = slider.getValue();
			}
		});

		JButton closeSlider = new JButton(Resources.done);
		closeSlider.setBounds(136, 83, 117, 38);
		qtySlider.add(closeSlider);
		closeSlider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sizeModal.setVisible(true);
				qtySlider.setVisible(false);
			}
		});

//		buttons panel
		JPanel mainButtons = new JPanel();
		mainButtons.setBounds(5, 18, 277, 338);
		mainButtons.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPane.add(mainButtons);
		mainButtons.setLayout(null);

		JLabel drinksLabel = new JLabel(Resources.drinks);
		drinksLabel.setBounds(41, 25, 46, 16);
		mainButtons.add(drinksLabel);
		
		JLabel cakesLabel = new JLabel(Resources.cakes);
		cakesLabel.setBounds(189, 25, 46, 16);
		mainButtons.add(cakesLabel);

		JButton coffeeBtn = new JButton(Resources.coffee);
		coffeeBtn.setBounds(9, 52, 117, 50);
		mainButtons.add(coffeeBtn);
		coffeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = Resources.coffee;
				qtySlider.setVisible(true);
				sizeModal.setVisible(false);
			}
		});

		JButton juiceBtn = new JButton(Resources.juice);
		juiceBtn.setBounds(10, 115, 117, 50);
		mainButtons.add(juiceBtn);
		juiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = Resources.juice;
				qtySlider.setVisible(true);
				sizeModal.setVisible(false);
			}
		});

		JButton cheeseCakeBtn = new JButton(Resources.cheesecake);
		cheeseCakeBtn.setBounds(147, 53, 117, 50);
		mainButtons.add(cheeseCakeBtn);
		cheeseCakeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = Resources.cheesecake;
				qtySlider.setVisible(true);
				sizeModal.setVisible(false);
			}
		});

		JButton spongeCakeBtn = new JButton(Resources.spongecake);
		spongeCakeBtn.setBounds(147, 115, 117, 50);
		mainButtons.add(spongeCakeBtn);
		spongeCakeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = Resources.spongecake;
				qtySlider.setVisible(true);
				sizeModal.setVisible(false);
			}
		});

//		panel borders
		JPanel quantityBorder = new JPanel();
		quantityBorder.setBorder(new LineBorder(Color.BLACK));
		quantityBorder.setBounds(5, 360, 403, 127);
		contentPane.add(quantityBorder);
		quantityBorder.setBackground(new Color(0, 0, 0, 64));

		JPanel sizeBorder = new JPanel();
		sizeBorder.setBorder(new LineBorder(Color.BLACK));
		sizeBorder.setBounds(286, 18, 122, 338);
		contentPane.add(sizeBorder);
		sizeBorder.setBackground(new Color(0, 0, 0, 64));
	}
}
