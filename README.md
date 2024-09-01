# java-opengl-2048

## Build instructions

### Init

`gradle init`

### Refresh dependencies

`./gradlew build --refresh-dependencies`

### Run

Linux/Mac:
`./gradlew run`
Windows:
`.\gradlew run`

## Git

### Check current branch and changes

`git status`

### Checking if pulling from Github is possible

`git remote update`
`git status -uno` → no output = up to date

### Pulling files from Github

`git pull`

### Creating branch

`git branch branch-name`
`git checkout branch-name`

or

`git checkout -b branch-name`

### Switch to branch

`git checkout branch-name`

### Switch to main branch

`git checkout`

### Merge changes on main branch with secondary branch

`git merge`

### Merge secondary branch with main branch

`git merge branch-name`

### Git commit

`git add <file>` where `<file>` can be a path to a file or folder or `*` (all files) or `-a` (edited files)
`git commit -m "message"`

### Push changes to Github on a new/unpushed branch

`git push -u origin branch-name` where `branch-name` is `main` for the default branch

### Push changes to Github on an already pushed branch

`git push`
