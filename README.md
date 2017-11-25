Trick Two
---------

1. Check if a string is well formed (special case: ignore within quotes) e.g.
```
    (...[...{...}...]...)      <- Well formed
    (...[...{...]...}...       <- Malformed
    (..."[...{...]...}"...)    <- Well formed (Special case)  
```

2. Count anagrams in a list of strings e.g.
```
    cat,far,act,car,arc,rac     <- 5 anagrams
```

