select id, titel, datum > Now(), tijd
from filmagenda
WHERE datum = (SELECT datum
              					FROM filmagenda
              					ORDER BY datum
				  					Limit 1)

