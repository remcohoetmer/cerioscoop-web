select id, titel, datum >= CURDATE()
from filmagenda
group by datum
having min(datum) > '2016-01-10'
   and min(datum) < '2016-05-19'