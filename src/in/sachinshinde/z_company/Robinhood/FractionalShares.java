package in.sachinshinde.z_company.Robinhood;

import java.util.*;

/*
        On our journey to democratize finance for all, we’ve created the concept of fractional shares.
        Fractional shares are pieces, or fractions, of whole shares of a company or ETF.

        However, exchanges only trade in whole shares.
        Robinhood is required to manage the fractional portion of each trade.

        If Robinhood has 0 shares of AAPL and then a customer wishes to purchase 1.5 AAPL shares,
            Robinhood will need to request 2 shares from the exchange and hold on to the remaining 0.5 shares.

        If another customer requests to purchase 0.4 shares of AAPL,
            Robinhood can use its inventory (0.5 shares) instead of going out to the exchange and
            will have 0.1 shares of AAPL remaining.

        If the third customer requests 0.5 shares, Robinhood can fill 0.1 shares out of inventory but
            will need to go to the exchange for an additional share
            leaving Robinhood's inventory at 0.6 shares.

            {{"APPL","B","150","100"},{"APPL","B","40","100"},{"APPL","B","50","100"}}

        If a customer requests a dollar based order, we need to convert it to the relevant number of shares and
            run through the above steps.

        Always ensure the firm has a positive quantity in inventory and has under one share after handling an order.
        There's no need for us to hold onto whole shares!

        Steps:
            [1] Handle buying fractional shares.
            [2] Handle selling fractional shares.
            [3] Ensure inventory is less than 1 after each order.
                    e.g. Customer sells AAPL for 0.75 and then another sells AAPL for 0.50 -- we have 1.25 inventory.
                    We can sell 1 share to the market and keep our inventory small at 0.25.
            [4] Ensure inventory is always non-negative after each order.
                    e.g. Inventory is 0.2 and the customer buys 0.5 shares: ensure we end up with 0.7 shares in inventory.
                    Always “flatten”! (steps 3+4)

        The final 2 digits of every integer is the decimal. e.g. 1000 = 10.00, 20 = 0.20, 100 = 1.

        Example scenario:

        Input:
        // One AAPL buy order for 0.42 shares. AAPL is currently worth $1.
        orders: [["AAPL","B","42","100"]]

        // Inventory for AAPL is currently 0.99 shares.
        inventory: [["AAPL","99"]]


        Expected Output:
        // The users buys 0.42 shares from inventory, leaving us with 0.57 shares.
        [["AAPL","57"]]

        Another example scenario:

        Input:
        // One AAPL buy order for $0.42. AAPL is currently worth $1, so that's 0.42 shares.
        orders: [["AAPL","B","$42","100"]]
        // Existing AAPL inventory is 0.50 shares.
        inventory: [["AAPL","50"]]

        Expected Output:
        // 0.50 - 0.42 = 0.08 shares leftover.
        [["AAPL","8"]]

        [execution time limit] 3 seconds (java)
        [memory limit] 1 GB
        ----------------------------------------------------------------------------------------------------------------
        [input] array.array.string orders

        A list of orders in the format of [$SYMBOL, $BUY_OR_SELL, $QUANTITY, $CURRENT_PRICE]. Each parameter is a string.

        $SYMBOL: Can be "AAPL", "GOOGL", "MEOOOOOW" or anything really.
        $BUY_OR_SELL: "B" or "S". B for BUY, S for SELL.
        $QUANTITY: Can be a number or a dollar amount (prefixed with $). e.g. "100" for 1 quantity or "$150" for $1.50.
        $CURRENT_PRICE: Current price of the symbol with no $ sign. e.g. "1000" for $10.

        ** All numbers are multiplied by 100 to store two significant digits. e.g. 100 = 1.00, 150 = 1.50, 1025 = 10.25 **
        ----------------------------------------------------------------------------------------------------------------
        [input] array.array.string inventory

        Inventory is a list of the inventory of each symbol.
        Each element in the list a 2 item list of [$SYMBOL, $QUANTITY] (remember quantity is multiplied by 100!).

        An example for AAPL of 0.50 shares and GOOGL of 0.75 shares would be:

        [["AAPL","50"],
         ["GOOG","75"]]
        ----------------------------------------------------------------------------------------------------------------
        [output] array.array.string

        The output is the final inventory of each symbol after iterating through each trade.
        This is expected to be in the same order and format as the inventory parameter.
        e.g.
            ["AAPL","58"],
             ["GOOG","50"]]
        ----------------------------------------------------------------------------------------------------------------

 */

public class FractionalShares {

    /*
            Steps:

                1.  Converting the inventory array to a map for easier access and updates.
                2.  Processing each order by determining the type (buy/sell) and the quantity,
                        converting dollar-based orders to share-based if necessary.
                3.  Updating the inventory accordingly, ensuring it remains positive and does not exceed one whole share.
                4.  Converting the inventory map back to the required output format after processing all orders.
     */

//    public String[][] updateInventory(String[][] orders, String[][] inventory) {
//
//        Map<String, Integer> inventoryMap = new HashMap<>();
//        for (String[] item : inventory) {
//            inventoryMap.put(item[0], Integer.parseInt(item[1]));
//        }
//
//        for (String[] order : orders) {
//            String symbol = order[0];
//            String type = order[1];
//            String quantityStr = order[2];
//            int currentPrice = Integer.parseInt(order[3]);
//
//            int quantity;
//            if (quantityStr.startsWith("$")) {
//                quantity = Integer.parseInt(quantityStr.substring(1)) * 100 / currentPrice;
//            } else {
//                quantity = Integer.parseInt(quantityStr) * 100 / currentPrice;
//            }
////            System.out.println("Qty : "+ quantity);
//
//            if (type.equals("B")) {
//                handleBuyOrder(inventoryMap, symbol, quantity);
//            } else if (type.equals("S")) {
//                handleSellOrder(inventoryMap, symbol, quantity);
//            }
//        }
//
//        String[][] result = new String[inventory.length][2];
//        int i = 0;
//        for (String[] item : inventory) {
//            result[i][0] = item[0];
//            result[i][1] = String.valueOf(inventoryMap.get(item[0]));
//            i++;
//        }
//
//        return result;

        // Convert inventory to a map for easy lookup and update
//        Map<String, Integer> inventoryMap = new HashMap<>();
//        for (String[] inv : inventory) {
//            inventoryMap.put(inv[0], Integer.parseInt(inv[1]));
//        }
//
//        // Process each order
//        for (String[] order : orders) {
//            String symbol = order[0];
//            String buyOrSell = order[1];
//            int quantityRequired = getQuantity(order[2], order[3]);
//
//            System.out.println("qty" + quantityRequired);
//            // Ensure the symbol is in the inventory map
//            inventoryMap.putIfAbsent(symbol, 0);
//
//            if (buyOrSell.equals("B")) {
//                handleBuyOrder(inventoryMap, symbol, quantityRequired);
//            }
//            else {
//                handleSellOrder(inventoryMap, symbol, quantityRequired);
//            }
//        }
//
//        return inventoryMap
//                .entrySet()
//                .stream()
//                .map(e -> new String[]{e.getKey(), String.valueOf(e.getValue())})
//                .toArray(String[][]::new);

        // Convert the inventory map back to the required output format
//        String[][] res = new String[inventory.length][2];
//        int i = 0;
//        for (String[] inv : inventory) {
//            res[i][0] = inv[0];
//            res[i][1] = String.valueOf(inventoryMap.get(inv[0]));
//            i++;
//        }
//        return res;
//    }

//    private static int getQuantity(String requiredQuantity, String currentPrice) {
//        if (requiredQuantity.startsWith("$")) {
//            // Dollar-based order
//            int dollarAmount = Integer.parseInt(requiredQuantity.substring(1));
//            int pricePerDollar = Integer.parseInt(currentPrice);
//            float price = ((float)(dollarAmount) / (pricePerDollar)) * pricePerDollar;
//            System.out.println("PP: "+price);
//            return (int) price;
//        }
//        else {
//            // Share-based order
//            return Integer.parseInt(requiredQuantity);
//        }
//    }

//    private static void handleBuyOrder(Map<String, Integer> inventoryMap, String symbol, int quantityRequired) {
//        int currentInventory = inventoryMap.get(symbol);
//        if (currentInventory >= quantityRequired) {
//            // Fulfill from inventory
//            inventoryMap.put(symbol, currentInventory - quantityRequired);
//        }
//        else {
//            // Need to buy more from the exchange
//            if(quantityRequired > 100) {
//                inventoryMap.put(symbol, 100 * (quantityRequired / 100 + 1) - (quantityRequired - currentInventory));
//            }
//            else {
//                inventoryMap.put(symbol, 100 - (quantityRequired - currentInventory));
//            }
//        }
//    }

//    private static void handleBuyOrder(Map<String, Integer> inventoryMap, String symbol, int quantity) {
//        int currentInventory = inventoryMap.getOrDefault(symbol, 0);
//
//        if(currentInventory >= quantity) {
//            inventoryMap.put(symbol, currentInventory - quantity);
//        }
//        else if (quantity + currentInventory >= 100) {
//            int extra = (quantity + currentInventory) % 100;
//            inventoryMap.put(symbol, extra);
//        } else {
//            inventoryMap.put(symbol, 100 - (quantity - currentInventory));
//        }
//    }

//    private static void handleSellOrder(Map<String, Integer> inventoryMap, String symbol, int quantityRequired) {
//        int currentInventory = inventoryMap.get(symbol);
//        int newInventory = currentInventory + quantityRequired;
//        if (newInventory >= 100) {
//            // Sell excess to the market, keep the remainder
//            inventoryMap.put(symbol, newInventory - (newInventory / 100 * 100));
//        }
//        else {
//            // Update inventory directly
//            inventoryMap.put(symbol, newInventory);
//        }
//    }

//    private static void handleSellOrder(Map<String, Integer> inventoryMap, String symbol, int quantity) {
//        int currentInventory = inventoryMap.getOrDefault(symbol, 0);
//
//        if (currentInventory + quantity >= 100) {
//            int remaining = (currentInventory + quantity) % 100;
//            inventoryMap.put(symbol, remaining);
//        } else {
//            inventoryMap.put(symbol, currentInventory + quantity);
//        }
//    }

    public String[][] updateInventory(String[][] orders, String[][] inventory) {
        // Convert inventory to a map for easier lookup and update
        Map<String, Integer> inventoryMap = new HashMap<>();
        for (String[] item : inventory) {
            inventoryMap.put(item[0], Integer.parseInt(item[1]));
        }

        // Process each order
        for (String[] order : orders) {
            String symbol = order[0];
            String type = order[1];
            int quantity = parseQuantity(order[2], Integer.parseInt(order[3]));

            // Get the current inventory for the symbol, defaulting to 0 if not present
            int currentInventory = inventoryMap.getOrDefault(symbol, 0);

            if (type.equals("B")) { // Buy order
                currentInventory = handleBuyOrder(currentInventory, quantity);
            } else if (type.equals("S")) { // Sell order
                currentInventory = handleSellOrder(currentInventory, quantity);
            }

            // Ensure inventory is non-negative and less than one share (100 in this representation)
            currentInventory = Math.max(0, currentInventory);
            currentInventory %= 100;

            // Update the inventory map
            inventoryMap.put(symbol, currentInventory);
        }

        return inventoryMap
                .entrySet()
                .stream()
                .map(e -> new String[]{e.getKey(), String.valueOf(e.getValue())})
                .toArray(String[][]::new);
    }

    private static int parseQuantity(String quantityStr, int currentPrice) {
        if (quantityStr.startsWith("$")) {
            // Convert dollar amount to shares
            double dollarAmount = Double.parseDouble(quantityStr.substring(1));
            double price = currentPrice / 100.0;
            return (int) Math.round(dollarAmount / price * 100);
        } else {
            // Return quantity as is
            return Integer.parseInt(quantityStr);
        }
    }

    private static int handleBuyOrder(int currentInventory, int quantity) {
        int remainingQuantity = quantity;

        if (currentInventory >= remainingQuantity) {
            currentInventory -= remainingQuantity;
            remainingQuantity = 0;
        } else {
            remainingQuantity -= currentInventory;
            currentInventory = 0;
        }

        return currentInventory + remainingQuantity;
    }

    private static int handleSellOrder(int currentInventory, int quantity) {
        currentInventory += quantity;

        if (currentInventory >= 100) {
            currentInventory -= 100;
        }

        return currentInventory;
    }

    public static void main(String[] args) {
        FractionalShares fractionalShares = new FractionalShares();
        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(
                new String[][]{{"APPL","B","150","100"},{"APPL","B","40","100"},{"APPL","B","50","100"}},
                new String[][]{{"APPL","00"}})));     //  [[APPL, 60]]

//        // Example usage
        String[][] orders = {{"AAPL", "B", "42", "100"}};
        String[][] inventory = {{"AAPL", "99"}};
        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 57]]
//
        orders = new String[][]{{"AAPL", "B", "$42", "100"}};
        inventory = new String[][]{{"AAPL","50"}};
        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 8]]
//
        orders = new String[][] {{"AAPL", "B", "42", "50"}};
        inventory = new String[][] {{"AAPL", "19"}};
        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 77]]

//        orders = new String[][] {{"AAPL", "B", "$42", "50"}};
//        inventory = new String[][] {{"AAPL", "19"}};
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 35]]
//
//        orders = new String[][] {{"AAPL", "B", "42", "150"}};
//        inventory = new String[][] {{"AAPL", "19"}};
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 77]]
//
//        orders = new String[][] {{"AAPL", "B", "$42", "150"}};
//        inventory = new String[][] {{"AAPL", "19"}};
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 91]]
//
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(
//                new String[][]{{"APPL","B","150","100"},{"APPL","B","40","100"},{"APPL","B","50","100"},{"GOOG","B","175","100"}},
//                new String[][]{{"APPL", "00"},{"GOOG","00"}})));     //  [[GOOG, 25], [APPL, 60]]
//
//        orders = new String[][]{{"AAPL", "S", "42", "100"}};
//        inventory = new String[][]{{"AAPL","50"}};
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 92]]
//
//        orders = new String[][]{{"AAPL", "S", "152", "100"}};
//        inventory = new String[][]{{"AAPL","90"}};
//        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));    //  [[AAPL, 92]]

        orders = new String[][] {{"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"},
                {"AAPL","B","$42","10"}};

        inventory = new String[][] {{"AAPL","0"}};
        System.out.println(Arrays.deepToString(fractionalShares.updateInventory(orders, inventory)));
    }
}