package com.example.ruburger.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ruburger.R;
import com.example.ruburger.model.Beverage;
import com.example.ruburger.model.Flavor;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Size;

import java.util.ArrayList;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.BeverageHolder> {

    private Context context;
    private ArrayList<Beverage> beverageList;

    public BeverageAdapter(Context context, ArrayList<Beverage> beverageList) {
        this.context = context;
        this.beverageList = beverageList;
    }

    public static class BeverageHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Spinner size_option;
        private ConstraintLayout parentLayout;

        private Handler handler = new Handler();
        private Runnable delayedPrompt;
        private int quantityClicked = 0;

        public BeverageHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            size_option = itemView.findViewById(R.id.size_option);
            parentLayout = itemView.findViewById(R.id.rowLayout);
        }

        public void bind(Beverage beverage) {
            // Fix button text
            if (beverage.getQuantity() == 0) {
                btn_add.setText("+");
            } else {
                btn_add.setText(String.valueOf(beverage.getQuantity()));
            }

            tv_name.setText(beverage.getFlavor().toString());
            updatePrice(beverage); // price display based on quantity

            // Set correct image
            switch (beverage.getFlavor()) {
                case COCA_COLA:
                    im_item.setImageResource(R.drawable.cola);
                    break;
                case DIET_COKE:
                    im_item.setImageResource(R.drawable.diet_coke);
                    break;
                case SPRITE:
                    im_item.setImageResource(R.drawable.sprite);
                    break;
                case ROOT_BEER:
                    im_item.setImageResource(R.drawable.root_beer);
                    break;
                case FANTA:
                    im_item.setImageResource(R.drawable.fanta);
                    break;
                case PEPSI:
                    im_item.setImageResource(R.drawable.pepsi);
                    break;
                case DR_PEPPER:
                    im_item.setImageResource(R.drawable.dr_pepper);
                    break;
                case LEMONADE:
                    im_item.setImageResource(R.drawable.lemonade);
                    break;
                case ICED_TEA:
                    im_item.setImageResource(R.drawable.iced_tea);
                    break;
                case MILK_SHAKE:
                    im_item.setImageResource(R.drawable.milk_shake);
                    break;
                case WATER:
                    im_item.setImageResource(R.drawable.water);
                    break;
                case FRUIT_PUNCH:
                    im_item.setImageResource(R.drawable.fruit_punch);
                    break;
                case JUICE:
                    im_item.setImageResource(R.drawable.juice);
                    break;
                case COFFEE:
                    im_item.setImageResource(R.drawable.coffee);
                    break;
                case TEA:
                    im_item.setImageResource(R.drawable.tea);
                    break;
                default:
                    im_item.setImageResource(R.drawable.cola);
                    break;
            }

            // Setup spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<>(itemView.getContext(),
                    android.R.layout.simple_spinner_item,
                    new String[]{"Small", "Medium", "Large"});
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            size_option.setAdapter(adapter);

            if (beverage.getSize() == Size.SMALL) {
                size_option.setSelection(0);
            } else if (beverage.getSize() == Size.MEDIUM) {
                size_option.setSelection(1);
            } else if (beverage.getSize() == Size.LARGE) {
                size_option.setSelection(2);
            }

            size_option.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        beverage.setSize(Size.SMALL);
                    } else if (position == 1) {
                        beverage.setSize(Size.MEDIUM);
                    } else if (position == 2) {
                        beverage.setSize(Size.LARGE);
                    }
                    updatePrice(beverage); // update price dynamically
                }

                @Override
                public void onNothingSelected(android.widget.AdapterView<?> parent) {}
            });

            btn_add.setOnClickListener(v -> {
                if (beverage.getQuantity() == 0) {
                    beverage.setQuantity(1);
                } else {
                    beverage.setQuantity(beverage.getQuantity() + 1);
                }
                btn_add.setText(String.valueOf(beverage.getQuantity()));
                updatePrice(beverage);

                if (delayedPrompt != null) {
                    handler.removeCallbacks(delayedPrompt);
                }

                delayedPrompt = () -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to Order");
                    alert.setMessage("Add " + beverage.getQuantity() + " " + beverage.getFlavor()
                            + " (" + beverage.getSize() + ")"
                            + " (" + String.format("$%.2f", beverage.price()) + ")?");

                    alert.setPositiveButton("Yes", (dialog, which) -> {
                        Toast.makeText(itemView.getContext(),
                                beverage.getQuantity() + " " + beverage.getFlavor() + " added!",
                                Toast.LENGTH_SHORT).show();
                        Beverage addedBeverage = new Beverage(beverage.getSize(), beverage.getFlavor());
                        addedBeverage.setQuantity(beverage.getQuantity());
                        Order.getInstance().addItem(addedBeverage);

                        // Reset everything after adding
                        beverage.setQuantity(0);
                        btn_add.setText("+");
                        updatePrice(beverage);
                    });

                    alert.setNegativeButton("No", (dialog, which) -> {
                        Toast.makeText(itemView.getContext(),
                                "Canceled adding " + beverage.getFlavor(),
                                Toast.LENGTH_SHORT).show();
                        // Reset everything after cancel
                        beverage.setQuantity(0);
                        btn_add.setText("+");
                        updatePrice(beverage);
                    });

                    alert.show();
                };

                handler.postDelayed(delayedPrompt, 2500); // 2.5 seconds delay
            });
        }

        private void updatePrice(Beverage beverage) {
            if (beverage.getQuantity() == 0) {
                // quantity = 0, show base price (for 1 item)
                double unitPrice;
                if (beverage.getSize() == Size.SMALL) {
                    unitPrice = 1.99;
                } else if (beverage.getSize() == Size.MEDIUM) {
                    unitPrice = 2.49;
                } else if (beverage.getSize() == Size.LARGE) {
                    unitPrice = 2.99;
                } else {
                    unitPrice = 0;
                }
                tv_price.setText(String.format("$%.2f", unitPrice));
            } else {
                // quantity > 0, use beverage.price() normally
                double totalPrice = beverage.price();
                tv_price.setText(String.format("$%.2f", totalPrice));
            }
        }

    }

    @NonNull
    @Override
    public BeverageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new BeverageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageHolder holder, int position) {
        Beverage beverage = beverageList.get(position);
        holder.bind(beverage);
    }

    @Override
    public int getItemCount() {
        return beverageList.size();
    }


}
