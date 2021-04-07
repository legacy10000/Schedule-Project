# Weekly Schedule Planner 

## Izundu Joshua Robert Onukwulu

This application will allow users to plan out every day of their week by the hour and present this plan in an easy to
read manner (end goal is similar to the UBC class schedule format).

I hope that it in its **full glory** it will be able to:
- Display the users schedule for that day as a small side application similar to widgets found on mobile devices
- Allow users to click and drag slots in the schedule to shift if something urgent comes up
- Remind the users on Sunday to set their schedule for the week
- Notify users when attempting to schedule a slot that is already scheduled that there
  is a conflict

These are the end game goals for the project, hence some may be out of my scope at the moment, but I will work 
hard towards them at least.

The intended audience for this project is me, and anyone who from the public who likes to see their days of the week 
planned in an hourly manner. With online learning being the norm right now, along with society possible shifting to
being more open to working/learning from home in the future, having an application such as this one a computer should 
still remain relevant for years to come. This project is of interest to me because I have yet to find an application 
which plans my days in a way that I like. Furthermore, I know it will be something that I will always use as I like 
being organized, so this project should have a good return value for me.

## User Stories

- As a user I want to be able to add plans/activities by the hour for a 24-hour period in a day
- As a user I want to be able to delete plans/activities by name when they occur for a 24-hour period in a day
- As a user I to be able to view my 24-hour plan for the day
- As a user I want to be able to name my schedule and specify what day of the week it occurs on
- As a user, I want to be able to save my schedule to file
- As a user, I want to be able to be able to load my schedule from file
- As a user, when I start the application, I want to be given the option to load my schedule from file

Phase 4: Task 2
For this section I made my code more robust by adding an exception to the Activity constructor in the Activities
class so that now it will only accept begin and stop integers with proper values, and I no longer need the 
'REQUIRES' clause there. Since this method was used almost everywhere in my code, many more classes besides just
Activities have become more robust.

