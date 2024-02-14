package com.example.dummyproject.presentation.ui.home.ui.faqs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    val question: String,
    val answer: String,
) : Parcelable

var aboutUs1 = Content(
    "About Us",
    "Welcome to Parentalize, your trusted online resource for parenting guidance, support, and\n" +
            "inspiration. We understand that being a parent is an incredible journey filled with joy, love,\n" +
            "and occasional challenges. Our platform aims to provide you with a reliable source of\n" +
            "valuable information, expert advice, and a supportive community of like-minded individuals."
)

var aboutUs2 = Content(
    "Our Belief",
    "At Parentalize, we firmly believe in the power of parenthood and the profound impact it has\n" +
            "on both children and parents. We are dedicated to empowering parents and caregivers with\n" +
            "the knowledge and tools they need to navigate the complexities of raising children. We\n" +
            "embrace the uniqueness of each family and understand that there is no one-size-fits-all\n" +
            "approach to parenting."
)

var aboutUs3 = Content(
    "Our Missio",
    "Our mission is to be your comprehensive resource for child development, health and\n" +
            "wellness, education, and parenting techniques. We strive to curate and produce high-quality,\n" +
            "informative articles, practical tips, and engaging content that addresses the diverse needs of\n" +
            "parents at every stage of their parenting journey."
)

var aboutUs4 = Content(
    "Comprehensive Resource",
    "Parentalize offers a wealth of resources covering various aspects of parenting. Whether you\n" +
            "are a new parent seeking guidance on infant care or a seasoned parent looking for\n" +
            "strategies to handle your teenager's challenges, we have you covered. Our content is\n" +
            "carefully crafted by an experienced team of writers, educators, and child development\n" +
            "experts to ensure it is relevant, reliable, and practical."
)

var aboutUs5 = Content(
    "Promoting Positive Parenting",
    "Parentalize is committed to promoting positive parenting practices and nurturing healthy\n" +
            "parent-child relationships. We believe that fostering a nurturing environment is crucial for\n" +
            "children to thrive. Our content and resources aim to provide guidance on effective parenting\n" +
            "techniques, communication strategies, and building strong bonds with your children."
)

var aboutUs6 = Content(
    "Thank You for Being a Part of Our Community",
    "We appreciate you being a part of our parenting community. Together, let's embrace the\n" +
            "joys, navigate the challenges, and celebrate the incredible privilege of raising remarkable\n" +
            "human beings."
)

var aboutUs7 = Content(
    "Our Child Habit Tracking App",
    "In addition to being a trusted resource for parenting information, we are proud to offer a\n" +
            "state-of-the-art child habit-tracking app. This app is designed to help parents track and\n" +
            "monitor their child's habits and routines effectively. With our app, you can easily create\n" +
            "customizable routines, set up specific tasks or activities, and schedule reminders to ensure\n" +
            "that your child stays on track.\n" +
            "Our child habit tracking app provides an intuitive interface that allows you to monitor your\n" +
            "child's progress, view historical data, and gain valuable insights into their habits. It is a\n" +
            "convenient tool for tracking meals, chores, and more. By utilizing the app, you can establish\n" +
            "healthy routines, promote positive habits, and encourage your child's development."
)

val aboutUs: List<Content> = listOf(
    aboutUs1, aboutUs2, aboutUs3, aboutUs4, aboutUs5, aboutUs6,
    aboutUs7
)

var faq1 = Content(
    "What is the purpose of the Habit-Tracking app?",
    "The habit-tracking app is designed to help you track and monitor your child's habits and\n" +
            "routines effectively. It provides a convenient way to keep a record of their daily activities."
)
var faq2 = Content(
    "How does the habit-tracking feature work?",
    "The habit-tracking feature allows you to create customizable routines and habits for your\n" +
            "child. You can set up specific tasks or activities, define their frequency, and schedule\n" +
            "reminders. The app provides an intuitive interface to monitor and update their progress."
)
var faq3 = Content(
    "Can I personalize the habits for my child?",
    "Absolutely! The app allows you to personalize the habits according to your child's needs."
)
var faq4 = Content(
    "Will I receive reminders for my child's habits?",
    "Yes, the app provides customizable reminders to help you and your child stay on track."
)
var faq5 = Content(
    "Can I track multiple children within the app?",
    "Certainly! The app supports tracking multiple children simultaneously. You can create\n" +
            "separate profiles for each child, customize their habits individually, and monitor their\n" +
            "progress independently. This feature is particularly beneficial for families with multiple\n" +
            "children.\n"
)
var faq6 = Content(
    " Is the app suitable for children of all ages?",
    "Yes, the app is designed to cater to children of all ages, from toddlers to teenagers. You can\n" +
            "adjust the habits and routines according to the specific age group and developmental stage\n" +
            "of your child. The flexibility of the app allows you to adapt it as your child grows."
)
var faq7 = Content(
    "Is my data secure within the app?",
    "The security and privacy of your data are of utmost importance to us. Our app employs\n" +
            "robust security measures to ensure the protection of your information. We utilize encryption\n" +
            "protocols and follow industry best practices to safeguard your data from unauthorized\n" +
            "access."
)
var faq8 = Content(
    "How can I get support or assistance with the app?",
    "If you have any questions or need assistance with the app, our support team is here to help.\n" +
            "You can reach out to us at contact@parentalize.com, and we'll be glad to assist you\n" +
            "promptly"
)

val faqs: List<Content> = listOf(faq1, faq2, faq3, faq4, faq5, faq6, faq7, faq8)

var privacyPolicy1 = Content(
    "Information We Collect",
    "Child Profile Information: We may collect and store the information provided by\n" +
            "parents or legal guardians when creating a child profile, such as the child's name,\n" +
            "age, and habits.\n" +
            "Usage Data: We collect anonymous usage data, such as app interactions, habits\n" +
            "tracked, and general usage patterns, to improve our app's functionality and user\n" +
            "experience.\n" +
            "Device Information: We may collect certain device information, such as device type,\n" +
            "operating system version, and unique device identifiers, to ensure compatibility and\n" +
            "technical support"
)
var privacyPolicy2 = Content(
    "Use of Information",
    "Child Habit Tracking: The child profile information is used to personalize the\n" +
            "habit-tracking experience and monitor your child's progress\nImprovement of App: Usage data helps us understand how users interact with the\n" +
            "app, identify areas for improvement, and enhance the overall user experience\nCustomer Support: Device information may be used to provide technical support\n" +
            "and troubleshoot issues you encounter while using the app."
)
var privacyPolicy3 = Content(
    "Data Security",
    "We take the security of your information seriously and have implemented appropriate\n" +
            "measures to protect it. However, it's important to note that no method of data transmission or\n" +
            "storage is 100% secure. While we strive to protect your information, we cannot guarantee\n" +
            "absolute security. By using our app, you acknowledge and accept these inherent security\n" +
            "risks."
)
var privacyPolicy4 = Content(
    "Sharing of Information",
    "Third-Party Services: We may engage trusted third-party service providers to assist\n" +
            "us in operating and improving our app. These providers are obligated to handle your\n" +
            "information securely and in accordance with applicable data protection laws.\nLegal Compliance: We may disclose information when required by law or if we\n" +
            "believe it is necessary to protect our rights, comply with legal proceedings, or enforce\n" +
            "our app's policies."
)
var privacyPolicy5 = Content(
    "Parental Control and Consent",
    "As a parent or legal guardian, you have the right to review, modify, or delete your child's\n" +
            "information within the app. You can also withdraw your consent for the collection and use of\n" +
            "your child's information at any time by contacting us."
)
var privacyPolicy6 = Content(
    "Updates to Privacy Policy",
    "We may update this Privacy Policy from time to time. Any changes will be reflected on this\n" +
            "page, and we encourage you to review the policy periodically. By continuing to use our app\n" +
            "after modifications to the policy, you consent to the updated practices."
)
var privacyPolicy7 = Content(
    "Contact Us",
    "If you have any questions, concerns, or requests regarding our Privacy Policy or the\n" +
            "handling of your information, please contact us at contact@parentalize.com"
)

val privacyPolicy: List<Content> = listOf(
    privacyPolicy1, privacyPolicy2, privacyPolicy3,
    privacyPolicy4, privacyPolicy5, privacyPolicy6, privacyPolicy7
)