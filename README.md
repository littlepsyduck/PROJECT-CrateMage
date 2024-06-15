# CrateMage - ProGameCup

> ProGameCup is a competition hosted by ProPTIT. After OOP Course for club member in ProPTIT, we will have a competition to test our knowledge and skills. This competition is a good chance for us to improve our coding skills and learn from each other.

## 1. Group Information

**Project Name:** Crate Mage

**Project Link:** [GitHub Link](https://github.com/littlepsyduck/PROJECT-CrateMage)

**Members:**
- Nguyễn Bá Việt Hoàng
- Hoàng Trung Hiếu
- Đặng Huyền Trang

**Mentors:**
- Nguyễn Quốc Hưng
- Nguyễn Đăng Minh

### Working Model

Team operates using the Scrum model and utilizes Linear to manage tasks. All tasks are meticulously tracked on Linear.
- Tracking: [Linear Link](https://linear.app/bdtproptit/team/NHOM4/all)

Each week, team meets to review completed work, collaboratively solve issues, and propose solutions for the upcoming week. This is followed by a demo session with the mentor to receive feedback and guidance.

### Version Control Strategy

Team uses Gitflow to manage code. Each member creates a branch from `develop` for their work, naming the branches using the format `feature/feature-name`. Upon completion, a Pull Request is created for code review and then merged into `develop`.
- Main branches:
    - `main`: Contains stable code that has been thoroughly tested and reviewed.
    - `develop`: Contains the latest code that has passed review and testing.
    - `feature/`: Branches that contain ongoing development work, which are short-lived and merged into `develop` upon completion.

![alt text](image.png)

At the end of each week, team merges `develop` into `main` to release a new version.



## 2. Project Introduction

> **Description:** Crate Mage is an engaging and strategic puzzle game where players take on the role of a powerful mage who manipulates crates to overcome obstacles, solve intricate puzzles, and defeat enemies. The game combines elements of classic block-pushing puzzles with magical abilities, creating a unique and challenging gameplay experience.

## 3. Main Features

- **Level Progression:** Advance through increasingly difficult levels, each designed with unique challenges and requiring innovative solutions.
- **Puzzle Mechanics:** Players must manipulate crates to navigate through levels and reach goals.
- **Time Clock:** A stopwatch will be activated at the start of the level and show the time when the player reaches goal.

## 4. Technology

### 4.1. Technologies Used
- **Programming Language:** Java
- **Framework:** libGDX
- **Graphics:** Adobe Photoshop and Adobe Illustrator
- **Sound:** Adobe Premiere Pro for sound editing
- **Version Control:** Git with GitHub for repository hosting

### 4.2 Project Structure

```
- assets 
  - button
  - character
  - levelSelect
  - login
  - map
  - menu
  - levelPass
  - sounds
  - tutorial
- core
  - common
  - view
  - model
  - controller
  - screen
  - CrateMage(main)
- desktop
  - DesktopLauncher
...
```

Explanation:
- **assets:** Contains resources like images and sounds.
- **core:**  Contains the main classes of the game, such as model, view, and controller.
- **desktop:** Contains platform-specific classes for running the game on different platforms.






## 5. Demo Images and Videos

**Demo Images:**
![Ảnh Demo](#)

**Demo Videos:**
[Video Link](#)






## 6. Issues Encountered

### Issue 1: [Mô tả vấn đề]
**Ví dụ:** Game gặp phải vấn đề hiệu năng kém, fps thấp dù không có nhiều đối tượng trên màn hình

### Actions Taken to Resolve

**Solution:** Do việc tạo object quá nhiều, nên dẫn tới tràn ram và giảm hiệu năng
- Sử dụng Design Pattern Object Pool để tái sử dụng object. Khi object không còn sử dụng, sẽ được đưa vào pool để sử dụng lại.

### Result

- Sau khi sử dụng Object Pool, hiệu năng game đã được cải thiện, fps tăng lên đáng kể. Từ *30fps lên 60fps* (Rõ ràng hơn với số liệu cụ thể)

### Vấn Đề 2: [Mô tả vấn đề]
**Ví dụ:** Có quá nhiều class quái khác nhau, dù chúng có nhiều điểm chung


### Hành Động Để Giải Quyết

**Giải pháp:** Sử dụng Design Pattern Builder để tạo các object quái với các thuộc tính khác nhau mà không cần tạo nhiều class. Ngoài ra sử dụng Strategy Pattern để tạo các hành vi khác nhau cho các object quái mà không cần tạo nhiều class.

### Kết Quả

- Sau khi sử dụng Builder và Strategy Pattern, việc tạo các object quái đã trở nên dễ dàng hơn, không cần tạo nhiều class. Có thể chỉ cần config các thuộc tính và hành vi cho object quái mà không cần tạo nhiều class.

## 7. Conclusion

**Achieved Results:** [Mô tả kết quả đạt được sau khi giải quyết các vấn đề]

**Future Development Direction:** [Mô tả hướng phát triển tiếp theo của dự án]