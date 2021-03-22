 ## How to clone this repository? ##
* Make sure Git is installed on your local machine if you plan to develop on it. Git is installed by default on most Linux/Mac distros. To check, type `git` in your command prompt (or terminal) and you should see a list of commands pop up if you have git. If you do not, see: https://git-scm.com/downloads.
* In the terminal, type `git clone https://YOURUSERNAME@github.com/jlau24/da-cs400-red-project-two.git`.
  * This will prompt you for your GitHub password. Enter it and cloning will start.
  * You may get a username/password deprecation notice in your emails. Password authentication will work until August 2021, so this is not an issue for us. For sake of simplicity, we will stick with password authentication.
* Enter the repository with `cd da-cs400-red-project-two`, or whatever directory you cloned the repository into. 
* Each member should work on their own branch. To create a new branch **and** checkout, enter: `git checkout -b <role-name-lowercase>`. For example, if you were the data wrangler, you could type `git checkout -b data-wrangler`.
  * Just so you are aware, this is a shortcut for these two commands (don't type these in):
    * `git branch data-wrangler`: Create a new branch
    * `git checkout data-wrangler`: Switch to branch
* You should be set to go after this.

## What  are the commands needed for development? ##
* `git add <file-name>`: Add a file to staging.
* `git commit -m <message>`: Commit whatever is in staging.
* `git branch <branch-name>`: Create a new branch.
* `git checkout <branch-name>`: Switch to a specific branch.
* `git fetch`: Fetch the remote repository's history and update your local repository with it.
* `git pull`: Pull the latest changes from the remote repository (make sure to fetch before you pull).
  * Probably won't use this much on this project.
* `git push`: Push your changes to the remote repository.
  * The first time you push you need to run: `git push --set-upstream origin <repository-name>`. For example, if the repository name was data-wrangler, then you run: `git push --set-upstream origin data-wrangler`.
  * Every subsequent push you can do: `git push`.
  * Every time you push it may prompt you to enter your username and password.
  
## Project Hierarchy ##
* There is a folder for each individual: datawrangler, frontend, backend, integrationmanager.
  * Make sure your work is in your folder. This includes dummy objects.
  * The integration manager will handle combining these files so that they work together later on.
