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
import androidx.recyclerview.widget.RecyclerView;

import com.example.ruburger.R;
import com.example.ruburger.model.Beverage;
import com.example.ruburger.model.Order;
import com.example.ruburger.model.Size;

import java.util.ArrayList;

/**
 * Adapter for displaying a list of Beverages in a RecyclerView.
 * Allows users to select size, add quantities, and add beverages to an order.
 * @author Eric Lin, Anish Mande
 */
public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.BeverageHolder> {

    private final Context context;
    private ArrayList<Beverage> beverageList;

    /**
     * Constructs a new BeverageAdapter.
     *
     * @param context      The context used for inflating layouts and resources.
     * @param beverageList The list of beverages to display.
     */
    public BeverageAdapter(Context context, ArrayList<Beverage> beverageList) {
        this.context = context;
        this.beverageList = beverageList;
    }

    /**
     * ViewHolder class that represents each beverage item view.
     */
    public static class BeverageHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Button btn_add;
        private Spinner size_option;

        private Handler handler = new Handler();
        private Runnable delayedPrompt;

        /**
         * Constructs a BeverageHolder and initializes the view components.
         *
         * @param itemView The item view layout for a beverage.
         */
        public BeverageHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            size_option = itemView.findViewById(R.id.size_option);
        }

        /**
         * Binds the beverage data to the UI components in the item view.
         *
         * @param beverage The beverage to bind to the view.
         */
        public void bind(Beverage beverage) {
            if (beverage.getQuantity() == 0) {
                btn_add.setText("+");
            } else {
                btn_add.setText(String.valueOf(beverage.getQuantity()));
            }

            tv_name.setText(beverage.getFlavor().toString());
            updatePrice(beverage);

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
                    updatePrice(beverage);
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

                handler.postDelayed(delayedPrompt, 1500);
            });
        }

        /**
         * Updates the displayed price for the beverage depending on its quantity and size.
         *
         * @param beverage The beverage to update the price for.
         */
        private void updatePrice(Beverage beverage) {
            if (beverage.getQuantity() == 0) {
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
                double totalPrice = beverage.price();
                tv_price.setText(String.format("$%.2f", totalPrice));
            }
        }

    }

    /**
     * Inflates the layout for a beverage item and creates a new ViewHolder.
     *
     * @param parent   The parent view group.
     * @param viewType The view type of the new view.
     * @return A new BeverageHolder instance.
     */
    @NonNull
    @Override
    public BeverageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new BeverageHolder(view);
    }

    /**
     * Binds the beverage data at the given position to the ViewHolder.
     *
     * @param holder   The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BeverageHolder holder, int position) {
        Beverage beverage = beverageList.get(position);
        holder.bind(beverage);
    }

    /**
     * Returns the total number of beverages in the list.
     *
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return beverageList.size();
    }


}
