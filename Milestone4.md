<table><tr><td> <em>Assignment: </em> IT114 Trivia Milestone4</td></tr>
<tr><td> <em>Student: </em> Ayanfeolu Olunuga (ajo47)</td></tr>
<tr><td> <em>Generated: </em> 12/13/2023 9:49:11 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-trivia-milestone4/grade/ajo47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone4 from the proposal document:&nbsp;<a href="https://docs.google.com/document/d/1h2aEWUoZ-etpz1CRl-StaWbZTjkd9BDMq0b6TXK4utI/view">https://docs.google.com/document/d/1h2aEWUoZ-etpz1CRl-StaWbZTjkd9BDMq0b6TXK4utI/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Categories can be configured before game begins </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of the UI showing category selection</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T17.55.02image.png.webp?alt=media&token=e27f8abb-1aca-4c33-8fff-e5e22c5a7b3d"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is a pregame screen where player selects a preffered category from a<br>dropdown list<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the category selection is handled on the server-side</td></tr>
<tr><td> <em>Response:</em> <p>When a player selects a category, the category is passed as a parameter<br>to the submitcategory() method in the client method. The category is then wrapped<br>as a payload to the server where its saved in the game room.<br>When selecting a random question, the system first checks if there is a<br>value assigned to the preferred category variable. If there is a preferred category,<br>the system selects only the lines whose category is the same as the<br>supplied one, otherwise the system picks a random question without any preference to<br>a specific category. The random question is then passed to client for display.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client will be able to upload new category/questions/answer (outside of an active game) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of the UI for adding related data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.07.31image.png.webp?alt=media&token=9dd5671c-2769-4398-911e-217f788533ee"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is the new question upload panel where a player inputs a new<br>question for the sytem<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot(s) of the custom data available in the game</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.09.47image.png.webp?alt=media&token=262f1fef-ba7f-414f-a941-01c4b7036e34"/></td></tr>
<tr><td> <em>Caption:</em> <p>The data provided by player<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.11.54image.png.webp?alt=media&token=233574ba-7720-4dda-adc7-dc81e0f7c8a9"/></td></tr>
<tr><td> <em>Caption:</em> <p>The base code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Add screenshot(s) of how the custom data is saved on the server</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.12.41image.png.webp?alt=media&token=82b14890-9b32-4bf5-a148-8f16184529b9"/></td></tr>
<tr><td> <em>Caption:</em> <p>My saved data in a textfile<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 4: </em> Briefly explain the related code logic for each part of this feature</td></tr>
<tr><td> <em>Response:</em> <p>The system provides a panel where users can input new question details. Since<br>the system is fixed to have two categories, the system provides a dropdown<br>functionality for the category input.&nbsp; After selecting a category, the player can then<br>input the question and possible answers. These possible answers are separated by commas.<br>The player also provides a correct answer for later option validation. When the<br>player clicks on the submit button, the system validates the entries and concatenates<br>them to a string, and passes them as a string to the server.<br>In the server, the question is saved to the questions.txt file. This question<br>can then be used by the system to randomly select a question<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client can mark themselves “away” to be skipped in the turn flow but still be in the game </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of the visual representation of someone "away"</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.33.41image.png.webp?alt=media&token=06d29494-569c-4137-9985-c3e5db2abb2d"/></td></tr>
<tr><td> <em>Caption:</em> <p>The UI message showing a player has marked themselves away<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.36.15image.png.webp?alt=media&token=1613bbc1-70c9-486f-a7a0-ceb3fe39b474"/></td></tr>
<tr><td> <em>Caption:</em> <p>A message gets sent to all players<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.37.33image.png.webp?alt=media&token=fffc9d53-503c-466d-ad69-a5a1397f94b9"/></td></tr>
<tr><td> <em>Caption:</em> <p>After confirming that he has marked himself away, player is redirected to rooms<br>panel<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code logic</td></tr>
<tr><td> <em>Response:</em> <p>Before the game starts, a player can mark himself as Ready, away or<br>spectator. When a player marks himself as away, a message is sent to<br>all members. the game then proceeds without the player, and the player doesn&#39;t<br>get any updates about the next round.&nbsp; This is because no ready signal<br>is sent on behalf of the player.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Client can join as a spectator </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) of what a spectator can see</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fajo47%2F2023-12-13T18.42.53image.png.webp?alt=media&token=296519eb-8356-48ac-b10a-0bdba63d3b35"/></td></tr>
<tr><td> <em>Caption:</em> <p>When a player marks themselves as spectator, they cannot participate in the game<br> and therefore they cannot see the correct answer. They can however send<br>messages in game chat<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the code handles spectators</td></tr>
<tr><td> <em>Response:</em> <p>When a player selects the spectator button, they cannot participate in the game.<br>This is because a spectator flag is sent to the server and the<br>client side, and any submission they make is invalidated by the spectator flag.<br>The player can however see messages sent to the chat but cannot submit<br>or see the right answer.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-001-F23/it114-trivia-milestone4/grade/ajo47" target="_blank">Grading</a></td></tr></table>