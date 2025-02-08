**Cart Service**
1 User can add the things to the cart after login.
2 When we recieve a medicine data to add that into the cart it should contain the user information.
3 If the price of the medicine change we should also update the cartitems price .
4 if the quantity of the cartitem for the particular medicine exceeds the medicine in the stock then we should show 
  a pop message to buy that much stock.
5 Put a maximum purchase limit for the specific medicine.

**Admin Module**
FetchLowStockMedicine()
1 While fetching the lowMedicine stock .I have taken the threshhold as 15 from the application properties .
  if same medicine having different batches then it means that more other stocks for that medicine has been reserved.So medicine having two or more batches can not come into 
  the fetch lowStock medicine list.
  When the chemist buys the medicine from the supplier then the medicine comes in the form of batches .Each batch having their expiry date mentioned .

  **Medicine**
  1 If a medicine gets banned from the governement and the customer has added that medicine into the cart then he is not allowed to order that medicine .
    Whenever a medicine is banned, cart items containing that medicine should be updated automatically.
    Add a trigger to update CartItem when Product.status = 'BANNED':
    Overall we will think on this functionality in the future.
    
**Prescription**
  1 There will not be any link between the Prescription and the CartItem .Prescription has the reference with Prescribed Items which has refrence to the medicine.
    This is because :
    Separation of Concerns – Prescription and Cart are two independent systems.
    Flexibility – A prescription can be approved partially, and some medicines may not be added to the cart.
    Scalability – The cart remains lightweight, allowing for high performance in large-scale applications.
    Better Query Optimization – Using joins rather than redundant foreign keys improves performance.

**Cart**
1 Each customer has only one active cart at a time, and it gets cleared after an order is placed.


