require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "RNSecureStorage"
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']

  s.authors      = package['author']
  s.homepage     = package['homepage']
  s.platforms    = { :ios => "10.0", :tvos => "9.2", :osx => "10.14" }

  s.source       = { :git => "https://github.com/talut/rn-secure-storage.git", :tag => "v#{s.version}" }
  s.source_files  = "ios/**/*.{h,m}"
  s.xcconfig = { 'SWIFT_OBJC_BRIDGING_HEADER' => '${POD_ROOT}/RNSecureStorage-Bridging-Header.h' }
  s.dependency 'React'
end
