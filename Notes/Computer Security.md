# Computer Security





### Public Key Certificate

公钥证书

* <span style="color:red">**Certificate** </span>contains name of user and user’s public key (and possibly other info)

  **Certificate**包含用户名和用户公钥(可能还有其他信息)

* It is **signed** by the issuer, a **Certificate Authority** (CA), such as VeriSign

  它是由颁发者，一个**认证机构** (CA)，如VeriSign **签名**的
  $$
  M = (Alice, Alice’s \ public \ key), S = [M]_{CA} \\
  Alice’s \  Certificate= (M, S)
  $$
  
* Signature on certificate is verified using CA’s public key:

  证书上的签名使用CA的公钥进行验证:

   Verify that $M = {S}_{CA}$

    

### Certificate Authority

证书颁发机构

* Certificate authority (CA) is a trusted 3rd party (TTP) -- creates and signs certificates

  证书颁发机构(CA)是一个可信第三方(TTP)--创建和签名证书

* Verify signature to verify integrity & identity of **owner of corresponding private key**

  验证签名以验证**对应私钥所有者**的完整性和身份

  * Does **not** verify the identity of the **sender** of certificate -- certificates are public keys!

    **不**验证证书的**发送者**的身份--证书是公钥!

* Big problem if CA makes a mistake (a CA once issued Microsoft certificate to someone else)

  如果CA出错，问题就大了(CA曾经将微软证书颁发给了其他人)

* A common format for certificates is X.509

  证书的常见格式是X.509



### PKI

* Public Key Infrastructure (PKI): the stuff needed to securely use public key crypto

  公钥基础设施(PKI):安全使用公钥加密所需的东西

  * Key generation and management

    密钥生成与管理

  * Certificate authority (CA) or authorities

    证书颁发机构(CA)或权威机构

  * Certificate revocation lists (CRLs), etc.

    证书吊销列表(crl)等。

  * No general standard for PKI

    PKI没有通用标准

  * We mention 3 generic “trust models”

    我们提到了3种通用的“信任模型”



#### PKI Trust Models

* Monopoly model

  垄断模式

  * One universally trusted organization is the CA for the known universe

    已知领域的CA是一个普遍可信的组织

  * Big problems if CA is ever compromised

    如果CA被入侵，问题就大了

  * Who will act as CA???

    谁将担任CA??

  * System is useless if you don’t trust the CA!

    如果你不相信CA，系统就是没用的!

* Oligarchy 寡头

  * Multiple trusted CAs

    多个可信ca

  * This is approach used in browsers today

    这是当今浏览器中使用的方法

  * Browser may have 80 or more certificates, just to verify certificates!

    浏览器可能有80个或更多的证书，只是为了验证证书!

  * User can decide which CAs to trust 

    用户可以决定信任哪个ca

* Anarchy model

  无政府状态模型

  * Everyone is a CA…

    每个人都是CA……

  * Users must decide who to trust

    用户必须决定信任谁

  * This approach used in PGP: “Web of trust”

    在PGP中使用的方法:“信任网络”

* Why is it anarchy? 

  为什么是无政府状态?

  * Suppose a certificate is signed by Frank and you don’t know Frank, but you do trust Bob and Bob says Alice is trustworthy and Alice vouches for Frank. Should you accept the certificate?

    假设一个证书是Frank签发的，你不认识Frank，但是你信任Bob, Bob说Alice是值得信任的，并且Alice为Frank担保。你应该接受证书吗?

* **Many** other trust models and PKI issues

  **其他**信任模型和PKI问题



## Confidentiality in the Real World

现实世界中的机密性

### Symmetric Key vs Public Key

* Symmetric key +’s

  对称密钥

  * **Speed**

    速度

  * No public key infrastructure (PKI) needed

    不需要公钥基础设施(PKI)

* Public Key +’s

  公钥+的值

  * **Signatures** (non-repudiation)

    **签名**(不可否认性)

  * No **shared** secret (but, private keys…)

    没有**共享**密钥(但是私钥…)

