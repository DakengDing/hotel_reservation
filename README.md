# hotel_reservation
This is a Hotel reservation application, it can help customer find a desired rooms and book them. 


![108933613-3718ac80-762a-11eb-97eb-69d3739d6413](https://user-images.githubusercontent.com/80197392/156843864-63b96884-d502-493c-be34-bb8c75d7f9b2.gif)

# Main Components of the App
## 1. CLI for the User Interface. 
User can enter commenads to search for available rooms, book rooms, and so on.
## 2. Java code
main businees logic for this app.
## 3. Java collections
the data of this app is stored in the Java collections (In-Memory Storage), such as users' names, room availability and so on.
![Untitled Workspace](https://user-images.githubusercontent.com/80197392/153729514-51142a9a-aa18-4275-b7ca-b36f626eb7ca.png)


# Application architecture
## 1. User interface (UI)
Hotel custmor will use the *main menu* to bool a room, and the administrator user will use *admin menu* for the administrative functions.
## 2. Resourves
Resourse layer will act as Application programming Interface (API) to the UI layer.
## 3. Services
Servics layer will commnicate with resourced and each other to build the vusiness logic and provide feedback to UI.
## 4. Data models
Data models will be used to represent the domain that use within the system (e.g.,rooms, reservations,and customers).

<img width="1159" alt="截屏2022-02-12 下午5 24 34" src="https://user-images.githubusercontent.com/80197392/153730465-9921d808-02a3-48af-a0aa-ca5173d4b74b.png">

