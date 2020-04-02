package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.*;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -2804446079853846996L;
	private ShopController shopController;

	private ItemFilter filter;

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();

		filter = new ItemFilter(shopController.getCurrentCategory());
		filter.getItemSpec().setCategory(shopController.getCurrentCategory());
		add(createPropertyCheckbox("Tanie bo polskie", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				filter.getItemSpec().setPolish(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		add(createPropertyCheckbox("Używany", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				filter.getItemSpec().setSecondhand(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		if (shopController.getCurrentCategory() == Category.BOOKS) {
			add(createPropertyCheckbox("Twarda oprawa", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					((Books) filter.getItemSpec()).setHardcover(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}

		if (shopController.getCurrentCategory() == Category.ELECTRONICS) {
			add(createPropertyCheckbox("Mobilny", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					((Electronics) filter.getItemSpec()).setMobile(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));

			add(createPropertyCheckbox("Gwarancja", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					((Electronics) filter.getItemSpec()).setWarranty(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}

		if (shopController.getCurrentCategory() == Category.MUSIC) {
			add(createPropertyCheckbox("Dołączone video", new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					((Music) filter.getItemSpec()).setVideoAttached(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}
	}

	private JCheckBox createPropertyCheckbox(String propertyName,
			ActionListener actionListener) {

		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}

}
