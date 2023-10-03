<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Ayanfeolu Olunuga (ajo47)</td></tr>
<tr><td> <em>Generated: </em> 10/2/2023 6:24:15 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-number-guesser/grade/ajo47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><ol><li><a href="https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f">https://gist.github.com/MattToegel/aced06400c812f13ad030db9518b399f</a><br></li></ol><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-10-02T21.41.02Screenshot%202023-10-02%20173918.png.webp?alt=media&token=7c0f8b92-562a-4be1-b657-74b324415010"/></td></tr>
<tr><td> <em>Caption:</em> <p>I implemented option 1,  which displays higher or lower as a hint,<br>after a wrong guess.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-10-02T21.47.08Screenshot%202023-10-02%20174654.png.webp?alt=media&token=17ba0dba-1b59-44f0-8e00-88bc5656c605"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is the code to display higher or lower after each wrong guess<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>The condition in the above code compares the player&#39;s guess to the randomly<br>generated number (number). If the guess is incorrect, it provides feedback by displaying<br>&quot;That&#39;s wrong&quot; and then checks whether the guess is higher or lower than<br>the target number. Depending on the comparison, it displays &quot;Hint: Go higher!&quot; or<br>&quot;Hint: Go lower!&quot; to guide the player.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-10-02T21.54.15Screenshot%202023-10-02%20175353.png.webp?alt=media&token=ac460655-ad1d-4237-a95b-49bd9d702a74"/></td></tr>
<tr><td> <em>Caption:</em> <p>Code to implement separate save files based on the name prompt<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-10-02T21.59.00Screenshot%202023-10-02%20175845.png.webp?alt=media&token=c3b5f831-f054-42bc-a329-71b6d3247557"/></td></tr>
<tr><td> <em>Caption:</em> <p>I implemented option 6, which saves a file based on the name prompt<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <div>When the game starts in the start() method, it first asks the player<br>for their name:</div><div>&nbsp; &nbsp; &nbsp; &nbsp; System.out.print("What's your name? ");</div><div>&nbsp; &nbsp; &nbsp; &nbsp;<br>playerName = input.nextLine();</div><div><br></div><div>After obtaining the player's name, it constructs a save file name<br>based on the player's name<br></div><div>&nbsp; &nbsp; &nbsp; &nbsp; fileName = playerName + "_ng4.txt";</div><div>The<br>save file created game contains data representing the state of the game for<br>a specific player</div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/Ayanfe0luwanimi/ajo47-IT114-001/pull/5">https://github.com/Ayanfe0luwanimi/ajo47-IT114-001/pull/5</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I had some issues with the M3 folder, since I was already using<br>it for class examples.&nbsp;<div>All those examples appeared in my pull request, because I<br>didn&#39;t merge them beforehand.</div><br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-number-guesser/grade/ajo47" target="_blank">Grading</a></td></tr></table>
