package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.*;

import java.awt.print.Book;

public class ItemFilter {

	private Item itemSpec;
	private Category category;

	public ItemFilter(Category category) {
		this.category = category;

		if (category == Category.BOOKS) {
			itemSpec = new Books();
		} else if (category == Category.ELECTRONICS) {
			itemSpec = new Electronics();
		} else if (category == Category.FOOD) {
			itemSpec = new Food();
		} else if (category == Category.MUSIC) {
			itemSpec = new Music();
		} else if (category == Category.SPORT) {
			itemSpec = new Sport();
		} else {
			itemSpec = null;
		}
	}

	public Item getItemSpec() {
		return itemSpec;
	}

	public boolean appliesTo(Item item) {
		if (itemSpec.getName() != null
				&& !itemSpec.getName().equals(item.getName())) {
			return false;
		}
		if (itemSpec.getCategory() != null
				&& !itemSpec.getCategory().equals(item.getCategory())) {
			return false;
		}

		// applies filter only if the flag (secondHand) is true)
		if (itemSpec.isSecondhand() && !item.isSecondhand()) {
			return false;
		}

		// applies filter only if the flag (polish) is true)
		if (itemSpec.isPolish() && !item.isPolish()) {
			return false;
		}

		if (category == Category.BOOKS) {
			// applies filter only if the flag (hardcover) is true
			if (((Books) itemSpec).isHardcover() && !((Books) item).isHardcover()) {
				return false;
			}
		}

		if (category == Category.ELECTRONICS) {
			// applies filter only if the flag (mobile) is true
			if (((Electronics) itemSpec).isMobile() && !((Electronics) item).isMobile()) {
				return false;
			}

			// applies filter only if the flag (warranty) is true
			if (((Electronics) itemSpec).isWarranty() && !((Electronics) item).isWarranty()) {
				return false;
			}
		}

		if (category == Category.MUSIC) {
			// applies filter only if the flag (videoAttached) is true
			if (((Music) itemSpec).isVideoAttached() && !((Music) item).isVideoAttached()) {
				return false;
			}
		}

		return true;
	}

}