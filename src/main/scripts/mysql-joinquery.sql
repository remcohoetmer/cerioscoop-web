CREATE VIEW showing_list AS 
	SELECT S.show_id, M.title, R.room_name, S.show_date, S.show_time
		FROM `show` S 
		JOIN movie M ON S.movie_id = M.movie_id
		JOIN room R ON R.room_id = S.room_id