# Part II: Access Control

Two parts to access control

访问控制分为两部分

* Authentication: Are you who you say you are? 

  认证:你是你所说的那个人吗?

* Determine whether access is allowed

  确定是否允许访问

  * Authenticate human to machine

    验证人类对机器

  * Or authenticate machine to machine

    或验证机器对机器

* Authorization: Are you allowed to do that? 

  授权:你可以这样做吗?

  * Once you have access, what can you do?

    一旦你有权限，你能做什么?

  * Enforces limits on actions

    对行动施加限制

* Note: “access control” often used as synonym for authorization

  注:“访问控制”通常用作授权的同义词


## Chapter 7: Authentication

### Are You Who You Say You Are?

* How to authenticate human a machine? 
* Can be based on...
  * Something you **know**
    * For example, a password
  * Something you **have**
    * For example, a smartcard
  * Something you **are**
    * For example, your fingerprint





Something You Know

* Passwords

* Lots of things act as passwords! 
  * PIN
  * Social security number 
  * Mother’s maiden name o Date of birth
  * Name of your pet, etc.



Password Experiment

* Three groups of users 3⁄4 each group advised to select passwords as follows
  * Group A: At least 6 chars, 1 non-letter 
  * Group B: Password based on passphrase    (This is the winner)
  * Group C: 8 random characters

* Results
  * Group A: About 30% of pwds easy to crack 
  * Group B: About 10% cracked
    * Passwords easy to remember 
  * Group C: About 10% cracked§
    * Passwords hard to remember



**Attacks on Passwords**

* Attacker could...

  * Target one particular account

  * Target any account on system

  * Target any account on any system

  * Attempt denial of service (DoS) attack

    企图拒绝服务(DoS)攻击

* Common attack path
  * Outsider $\rightarrow$ normal user $\rightarrow$ administrator
  * May only require **one** weak password!





**Password Retry**

* Suppose system locks after 3 bad passwords. How long should it lock?

  * 5 seconds

  * 5 minutes

  * Until SA restores service

    直到SA恢复服务 (SA service administrator)

* What are +’s and -’s of each?

eg. Like doctor ?



**Password File?**

* Bad idea to store passwords in a file
* But we need to verify passwords
* Cryptographic solution: hash the pwd

  * Store $y = h(password)$

  * Can verify entered password by hashing  

  * If Trudy obtains “password file,” she does not obtain passwords
* But Trudy can try a forward search
  * Guess $x$ and check whether $y = h(x)$



**Dictionary Attack**

* Trudy pre-computes h(x) for all x in a dictionary of common passwords

  Trudy对通用密码字典中的所有x预先计算h(x)

* Suppose Trudy gets access to password file containing hashed passwords

  假设Trudy可以访问包含哈希密码的密码文件

  * She only needs to compare hashes to her pre- computed dictionary

    她只需要将哈希值与预先计算的字典进行比较

  * After one-time work, actual attack is trivial 

    一次性工作后，实际攻击是微不足道的


* Can we prevent this attack? Or at least make attacker’s job more difficult?

  我们能阻止这种攻击吗?或者至少让攻击者的工作更加困难?



elite hacker 

> "Elite hacker"（精英黑客）是一个术语，用于描述在计算机安全和黑客领域中具有高度技能和经验的个人。这些黑客通常对计算机系统、网络和软件的工作原理有深入的了解，能够找到和利用这些系统的漏洞。





**Salt**

* Hash password with salt

  加盐的哈希密码

* Choose random salt $s$ and compute 
  $$
  y = h(password, s)
  $$
  and store (s,y) in the password file

* Note: The salt s is not secret

  盐s不是秘密

* Easy to verify salted password

  易于验证加盐密码

* But Trudy must re-compute dictionary hashes for each user

  但是Trudy必须重新计算每个用户的字典散列值

  * Lots more work for Trudy!

    特鲁迪还有很多工作要做! 









**Other Password Issues**

其他密码问题

* Too many passwords to remember o Results in password reuse

  太多的密码难以记住，导致密码重用

  * Why is this a problem?

    为什么会出现这个问题?

* Who suffers from bad password? 

  谁遭受了糟糕的密码?

  * Login password vs ATM PIN

    登录密码与ATM密码

* Failure to change default passwords

  修改默认密码失败

* Social engineering

  社会工程

* Error logs may contain “almost” passwords 

  错误日志可能包含“几乎”的密码

* Bugs, keystroke logging, spyware, etc.

   bug、击键记录、间谍软件等
