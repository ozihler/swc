# Init a new project
```
ng new news -t -s -p "nw" --routing -g --strict

Description:
ng new news 
-t // --inline-template
-s // --inline-style
-p "nw" // --prefix nw (<nw-xy>)
--routing 
-g // don't initialise git
--strict // use strict type checks
```

# Init a new feature (ngrx)
```
ng g feature books/book --module books/books --group --api
--module: attach feature to this module
--group: actions/reducers/effects have their own sub folder
--api: basic scaffolding for fetching data from an API
```