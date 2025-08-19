Module 1 | Version Control and Introduction to Software Development Assignment
==============================================================================

This is an assigment I made for the Summer semester of 2025 for my Object Oriented Programming class.
I was tasked to create an exception class that catches any username or password that does not adhere
to the requirements listed. I decided to have some fun and make some weird requirements! I also have 
left all the original comments because I thought they would be helpful since they had to be quite detailed
for the assignment.


Original notes on the project
-----------------------------


```
// Autumn Skye
// COP-3330C 33777
// May 10, 2025

/*
*  Project requirements:
*      We must decide on 2 distinct rules for both the username and the password (4 total unique rules). We
*      then need to create an exception class which accepts two parameters and extends Exception. We also need
*      to create a new class that takes in the same parameters and validates the username and password against
*      the rules chosen. In the main file we create 4 valid users using the PasswordVerify Class and prints all
*      four at the end.
*/

/*
*      Inputs and Outputs (Including the testing inputs):
*          User 1:
*              Enter a username and password:
*                  "hiii"
*                  "123"
*              Username errors/retry:
*                  Error = hiii does not contain meow. Possibly try hiiimeow
*                  "meow"      -->  Error = meow does not contain ^-^. Possibly try meow^-^
*                  "meow ^-^"  -->  Error = meow ^-^ contains spaces. Possibly try meow^-^
*                  "meow^-^"   -->  Username Success!
*              Onto the passwords!
*                  Error = 123 is too short. Possibly try 12312121212
*                  "1234567890101112"    -->  Error = Password contains the same character twice. Possibly try 123456789101ij2
*                  "meow space long"     -->  Error = Password contain c, a, or t. Possibly try meow spsve long
*                  "meow spe longer 02"  -->  Error = Password contains spaces. Possibly try meowspelonger02
*                  "meow^-^12345678"     -->  Error = Password matches your username. Possibly try 87654321^-^woe
*                  "qweryuiop123456"     -->  Password success!
*              User successfully created!
*
*          User 2:
*              Enter a username and password:
*                  "meow^-^"
*                  "user01psword02"
*              Username errors/retry:
*                  Error = Username has already been taken. Possibly try meow^-^79
*                  "meow^-^02"  -->  Username Success!
*              Onto the passwords!
*                  Password Success!
*              User created!
*
*          User 3:
*              Enter a username and password:
*                  "meow^test-03^"
*                  "user03psword03"
*              Username errors/retry:
*                  Username Success!
*              Onto the passwords!
*                  Password Success!
*              User created!
*
*          User 3:
*              Enter a username and password:
*                  "meow^-^04"
*                  "user04psword04"
*              Username errors/retry:
*                  Username Success!
*              Onto the passwords!
*                  Password Success!
*              User created!
*
*      Outputs all the usernames and passwords once they are all successfully created!
*/
```
