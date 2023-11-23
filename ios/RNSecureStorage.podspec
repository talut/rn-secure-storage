require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "RNSecureStorage"
  s.version      = package["version"]
  s.summary      = "RNSecureStorage"
  s.description  = package["description"]
  s.homepage     = package["homepage"]
  s.license      = { :type => package["license"], :file => "./LICENSE.md" }
  s.author       = { "Talut Tasgiran" => "info@taluttasgiran.com.tr" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/talut/rn-secure-storage.git", :tag => "v#{s.version}" }
  s.source_files  = "**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"

end