README file for Kennel-Server

*********************************************
INSTALLATION / STARTING THE SERVER
*********************************************

Requirements:

- Java 7+
- Maven 3+

1 - Navigate to the /target folder
2 - You will see a .jar file named 'kennel-server-1.0-SNAPSHOT.jar'. To run this, execute the command
    'java -jar kennel-server-1.0-SNAPSHOT.jar'. This will start the server on the localhost domain under port 80
3 - Open your browser and navigate to 'localhost:80'



*********************************************
KENNEL BOOKING TUTORIAL
*********************************************

The kennel booking system can be used to book dogs into a kennel.

After starting the dog kennel server for the first time, navigating to 'localhost:80' with take you to a login page.

********* LOGGING IN *********
The server has a number of dummy users that are allowed to log into the booking system. If a valid user is not given,
you will be prevented from logging in. Furthermore if you do not enter a username or password, attempting to log in
will give you an error message on the field left blank.

TRY THIS:
Use one of the following dummy users to log into the system

Username: Chris , Password: password1
Username: Tom , Password: password2
Username: Jess , Password: password3
Username: Alex, Password: password4

After clicking login, you will be taken to the main booking page. Here you will see your username, a logout link,
information on the state of the kennel and 2 forms for booking a dog into the kennel and checking a dog out.

Booking a dog into the kennel requires there to be room for the dog in the kennel, and a name must be provided.

TRY THIS:
Add a dog with no name - You should be shown an error message
Add a medium dog with a name - The kennel state will update and you will be shown a confirmation message
Add 2 large dogs - The first will be accepted but the second will be rejected because there isn't room in the kennel


Checking a dog out of the kennel requires the user to be the owner of the dog (IE the one who booked the dog in).

TRY THIS:
Checkout a dog with no name - You will be prompted for a dogs name
Checkout a dog that you have booked in - A confirmation message will be shown
Try checking out a dog that you didn't book in - You will be given a message telling you that you don't have a dog
booked in under that name.

Dogs that are booked into the kennel are stored against the user.

TRY THIS:
Book a dog into the kennel and click logout at the top of the page
Then log back in and checkout the dog.

Try logging in as a different user after logging out. You will notice the dog you booked in with the first user can be
seen in the kennel state. However the system will not allow you to checkout the dog.

The last element of the system, is the time shown at the base of the page. This will update if you refresh the page.

*******************************
EXTRA
*******************************

To keep the user in the confines of the application, the user will not be able to navigate to the login page, after
logging in.

TRY THIS:
Log in and navigate to 'localhost:80/login' - You will be redirected back to the kennel booking page

*********************************************************************************************************************

Server Author: Chris Brown
