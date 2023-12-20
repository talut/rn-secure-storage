require "json"
package = JSON.parse(File.read('package.json'))
Pod::Spec.new do |s|
  s.name         = "RNSecureStorage"
  s.version      = "1.0.82"
  s.summary      = "RNSecureStorage"
  s.description  = <<-DESC
                    Secure Storage for React Native (Android & iOS)
                   DESC
  s.homepage     = "https://github.com/talut/rn-secure-storage"
  s.license      = { :type => "MIT", :file => "./LICENSE.md" }
  s.author       = { "Talut Tasgiran" => "info@taluttasgiran.com.tr" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/talut/rn-secure-storage.git", :tag => "1.0.82" }
  s.source_files  = "**/*.{h,m}"
  s.requires_arc = true
  s.dependency "React"
  #s.dependency "others"
end