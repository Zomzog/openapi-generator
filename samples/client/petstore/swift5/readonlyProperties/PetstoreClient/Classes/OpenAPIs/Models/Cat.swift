//
// Cat.swift
//
// Generated by openapi-generator
// https://openapi-generator.tech
//

import Foundation

public struct Cat: Codable, Hashable {

    public private(set) var className: String
    public private(set) var color: String? = "red"
    public private(set) var declawed: Bool?

    public init(className: String, color: String? = "red", declawed: Bool? = nil) {
        self.className = className
        self.color = color
        self.declawed = declawed
    }

}
