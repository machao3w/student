# Springboot 模拟学生管理系统
本练习项目主要功能是对全校学生进行多条件查询，以及对全校学生成绩进行排名统计。

## 前端主要采用bootstrap-table插件，后端数据访问采用通用mapper


# 表设计
## 学生表
```
CREATE TABLE `student_info` (
  `number` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `classes` int(11) DEFAULT NULL,
  `name_english` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

## 期中成绩表
```
CREATE TABLE `grade_mid` (
  `number_m` varchar(255) NOT NULL,
  `chinese_mid` varchar(255) DEFAULT NULL,
  `math_mid` varchar(255) DEFAULT NULL,
  `english_mid` varchar(255) DEFAULT NULL,
  `physics_mid` varchar(255) DEFAULT NULL,
  `chemistry_mid` varchar(255) DEFAULT NULL,
  `geography_mid` varchar(255) DEFAULT NULL,
  `politics_mid` varchar(255) DEFAULT NULL,
  `history_mid` varchar(255) DEFAULT NULL,
  `biography_mid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`number_m`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

## 期末成绩表
```
CREATE TABLE `grade_final` (
  `number_f` varchar(255) NOT NULL,
  `chinese_final` varchar(255) DEFAULT NULL,
  `math_final` varchar(255) DEFAULT NULL,
  `english_final` varchar(255) DEFAULT NULL,
  `physics_final` varchar(255) DEFAULT NULL,
  `chemistry_final` varchar(255) DEFAULT NULL,
  `geography_final` varchar(255) DEFAULT NULL,
  `politics_final` varchar(255) DEFAULT NULL,
  `history_final` varchar(255) DEFAULT NULL,
  `biography_final` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`number_f`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

# 效果展示
## 学生信息查询
![学生信息查询](https://github.com/machao3w/student/blob/master/img/QQ%E6%88%AA%E5%9B%BE20190822135054.png)
## 学生成绩查询
![学生成绩查询](https://github.com/machao3w/student/blob/master/img/QQ%E6%88%AA%E5%9B%BE20190822135141.png)
