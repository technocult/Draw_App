package com.Draw_App.service.util;

import com.Draw_App.model.entity.Gift;

import java.util.Comparator;

public class GiftPriceDescComparator implements Comparator<Gift> {
    public GiftPriceDescComparator() {
    }

    @Override
    public int compare(Gift g1, Gift g2) {
        return g2.getGiftPrice()-g1.getGiftPrice();
    }
}

