--was entered in seconds, should have been in miliseconds, and use local time
update revinfo 
set revtstmp = 1459429200000 
where rev = 1; 
