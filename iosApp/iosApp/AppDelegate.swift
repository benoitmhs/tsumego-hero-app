//
//  AppDelegate.swift
//  iosApp
//
//  Created by Benoît on 24/01/2025.
//  Copyright © 2025 orgName. All rights reserved.
//
//import UIKit
//import TsumegoHero
//
//@UIApplicationMain
//class AppDelegate: NSObject, UIApplicationDelegate {
//    var window: UIWindow?
//
//    let rinku = RinkuIos.init(deepLinkFilter: nil, deepLinkMapper: nil)
//    
//    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
//        rinku.onDeepLinkReceived(url: url.absoluteString)
//        return true
//    }
//
//    func application(_ application: UIApplication, continue userActivity: NSUserActivity, restorationHandler: @escaping ([UIUserActivityRestoring]?) -> Void) -> Bool {
//        if userActivity.activityType == NSUserActivityTypeBrowsingWeb, let url = userActivity.webpageURL {
//            let urlString = url.absoluteString
//            rinku.onDeepLinkReceived(userActivity: userActivity)
//        }
//        return true
//    }
//}
