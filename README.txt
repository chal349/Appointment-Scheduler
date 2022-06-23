01-07-2022
----------------------
APPOINTMENT SCHEDULER 1.0
Author: Corey Hall
coreyrichardhall@gmail.com
----------------------
IDE Information:
IDE: IntelliJ IDEA 2021.1.1 (Community Edition)
Build #IC-211.7142.45, built on April 30, 2021
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
System: Windows 10 Home x64

Libraries:
Java JDK: Java SE 11.0.11
JavaFX SDK: 11.0.2
MySQL Driver: MySQL Connector 8.0.25

-----------------------
Instructions:
To use the application, set the root folder as source, and create
a build configuration that points to the main class, "sample.Main".

The default login information is "test" for both username and password.
----------------------

This application can be used to manage appointments for different customers in any timezone using a relational database.

Functionalities:
Login screen displays information based on users language settings.
All appointment times are stored "UTC" in mySQL database. Appointment times are converted to users local timezone upon login.
Users can Create, Update, Delete appointments.
Users can Create, Update, Delete customers.
View appointments by timeframe (day, week, or month)
Alerts the user when there is an appointment soon (within 15 minutes)

----------------------
Reporting Functionalities

The application can generate the following reports:
1. Tracking the number of appointments by month
2. Tracking the number of appointments by week
3. Tracking the number of appointment types in a given month
4. Tracking the number of appointment types in a given week
5. Showing all types of appointments and the number of each type of appointment in a given month
6. Showing all types of appointments and the number of each type of appointment in a given week
7. Showing a schedule of appointments for a given contact in a given month
8. Showing a schedule of appointments for a given contact in a given week

