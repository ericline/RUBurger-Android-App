package com.example.ruburger.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ruburger.R;
import com.example.ruburger.activity.BeverageAdapter;
import com.example.ruburger.model.Beverage;
import com.example.ruburger.model.Flavor;
import com.example.ruburger.model.Size;

import java.util.ArrayList;

public class BeveragesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Beverage> beverageList;
    private BeverageAdapter beverageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);

        recyclerView = findViewById(R.id.beverage_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        beverageList = new ArrayList<>();
        loadBeverages();

        beverageAdapter = new BeverageAdapter(this, beverageList);
        recyclerView.setAdapter(beverageAdapter);
    }

    private void loadBeverages() {
        Beverage cola = new Beverage(Size.MEDIUM, Flavor.COCA_COLA);
        cola.setQuantity(0);
        Beverage diet_coke = new Beverage(Size.MEDIUM, Flavor.DIET_COKE);
        diet_coke.setQuantity(0);
        Beverage sprite = new Beverage(Size.MEDIUM, Flavor.SPRITE);
        sprite.setQuantity(0);
        Beverage root_beer = new Beverage(Size.MEDIUM, Flavor.ROOT_BEER);
        root_beer.setQuantity(0);
        Beverage fanta = new Beverage(Size.MEDIUM, Flavor.FANTA);
        fanta.setQuantity(0);
        Beverage pepsi = new Beverage(Size.MEDIUM, Flavor.PEPSI);
        pepsi.setQuantity(0);
        Beverage dr_pepper = new Beverage(Size.MEDIUM, Flavor.DR_PEPPER);
        dr_pepper.setQuantity(0);
        Beverage lemonade = new Beverage(Size.MEDIUM, Flavor.LEMONADE);
        lemonade.setQuantity(0);
        Beverage iced_tea = new Beverage(Size.MEDIUM, Flavor.ICED_TEA);
        iced_tea.setQuantity(0);
        Beverage milk_shake = new Beverage(Size.MEDIUM, Flavor.MILK_SHAKE);
        milk_shake.setQuantity(0);
        Beverage water = new Beverage(Size.MEDIUM, Flavor.WATER);
        water.setQuantity(0);
        Beverage fruit_punch = new Beverage(Size.MEDIUM, Flavor.FRUIT_PUNCH);
        fruit_punch.setQuantity(0);
        Beverage juice = new Beverage(Size.MEDIUM, Flavor.JUICE);
        juice.setQuantity(0);
        Beverage coffee = new Beverage(Size.MEDIUM, Flavor.COFFEE);
        coffee.setQuantity(0);
        Beverage tea = new Beverage(Size.MEDIUM, Flavor.TEA);
        tea.setQuantity(0);

        beverageList.add(cola);
        beverageList.add(diet_coke);
        beverageList.add(sprite);
        beverageList.add(root_beer);
        beverageList.add(fanta);
        beverageList.add(pepsi);
        beverageList.add(dr_pepper);
        beverageList.add(lemonade);
        beverageList.add(iced_tea);
        beverageList.add(milk_shake);
        beverageList.add(water);
        beverageList.add(fruit_punch);
        beverageList.add(juice);
        beverageList.add(coffee);
        beverageList.add(tea);
    }
}
