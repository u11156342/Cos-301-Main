--======================================================================================
TALES ESTATE README FILE
--======================================================================================

1.	Extract glassfish4.zip
2. 	open netbeans
3. 	Open the projects.
4. 	Click the Services tab and under Servers delte Glassfish Server 3.1.2
5. 	Right click servers and add a new one, choose glassfish and click next,click
	browse and navigate to extracted glassfish folder,click finish.
6. 	Copy the auth.dll and serverConfig text files into yout extracted glassfish folder under
	glassfish > domains > domain1 or under config if pref doesnt work.
7.	Copy sqljdbc4.jar to the extracted glassfish folder under glassfish > lib
8.	Link the server to the jar.
9. 	edit the serverConfig textfile to point to the location of the pictures.
10.	Open sql server R2 and run TalesEstate.sql
11.	Restore TalesProd.bak

12. Deploy the server
13. Run the applet.

Common problems:

		Pics cant load,you either didnt run the script or the path inside server config is wrong.

		Cant find serverConfig even when it is in the folder,make sure that the File is named ServerConfig.txt
		and that the .txt is the extenstion and not part of the file name.
		
		ServerConfig found but pictures dont load: make sure that the path inside the ServerConfig has escaped
		slashes such as C:\\Users\\user\\Downloads\\Cos-301-Main-master\\TalesEstateServer\\Images\\
		and that it ends with 2 \\

		Cant load auth.dll: This is a common and big problem, if all steps above are followed and this
		error is still recieved then the glassfish enviroment is setup in a weird way, try putting it in the config 		folder, best to contact
		the creator of this file to recieve more support.

