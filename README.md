# Domain Driven Design

I want to share a little code about some develop that I did, applying concepts of DDD. My focus was to construct an aggregate root class called Building, that controls all the flow below them, as the following diagram:

![](https://github.com/mdymen85/appbuilding/blob/main/appbuilding.drawio.png)

Here, as I explained in the first paragraph, all the changes must happend through the aggregate root class; so in that case, we can have a consistent hierarchy, without any danger of uncontrolled change.

