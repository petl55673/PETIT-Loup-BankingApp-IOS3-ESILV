# PETIT-Loup-BankingApp-IOS3-ESILV
First of all we ensure that the user is the right one starting the app with a fingerprint autentication. If the fingerprint matches the one of the app owner, the user may login by clicking on the login button.

![158282143_268617294845767_6050899392903516621_n](https://user-images.githubusercontent.com/75316728/110249475-8342e580-7f76-11eb-99a3-d9c1b37fb7f1.jpg)
![158463491_192849302177536_547590551057005210_n](https://user-images.githubusercontent.com/75316728/110249538-c735ea80-7f76-11eb-9671-0d7ebf4dfcbe.jpg)

After a successfull login, all of the user's accounts are displayed and scrollable for extended length. A save button is used, in case the user wants to locally save his accounts so that he can access his accounts information even if he is offline. The load button will load the local files if needed, such as in offline mode. And finally the refresh button just refreshes the page, if the API is modified so will our page.


![158651595_896223347834168_4237914463842858712_n](https://user-images.githubusercontent.com/75316728/110249703-afab3180-7f77-11eb-9359-4874e2247c15.jpg)
![158275789_336953811084763_1107833888451980155_n](https://user-images.githubusercontent.com/75316728/110249516-a79ec200-7f76-11eb-849b-28770af5f6d5.jpg)

We save all of the user's data on the phone's internal storage which means that it can only be accessed by the app itself, which should be enough.

Here is an exemple of offline use :
First we need to at least save once on an online use.

![158676959_439861137230955_5710675392453005290_n](https://user-images.githubusercontent.com/75316728/110249847-8b9c2000-7f78-11eb-94eb-22b893f78c51.jpg)

then we quit the app, go in airplane mode and launch the app. After login in here is what we have: 


![158049278_138235141515951_2139955635248997753_n](https://user-images.githubusercontent.com/75316728/110249448-70301580-7f76-11eb-9e84-26f4c40afc2f.jpg)

We need to load the local files, after loading here is what we get: 

![158059584_2948858105347482_6990404120066846876_n](https://user-images.githubusercontent.com/75316728/110249890-cbfb9e00-7f78-11eb-95eb-c3e0b70c4eb6.jpg)


To ensure that the API url isn't recoverable we need to hide it:
The API url is hidden in the gradle.properties and we access the key using the buildconfig. The file that contains the data is protected by the phone's own system.




